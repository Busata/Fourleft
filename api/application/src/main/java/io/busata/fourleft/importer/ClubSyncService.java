package io.busata.fourleft.importer;

import io.busata.fourleft.api.messages.ClubEventEnded;
import io.busata.fourleft.api.messages.ClubEventStarted;
import io.busata.fourleft.api.messages.ClubInactive;
import io.busata.fourleft.api.messages.LeaderboardUpdated;
import io.busata.fourleft.domain.clubs.models.Club;
import io.busata.fourleft.domain.clubs.models.Event;
import io.busata.fourleft.domain.clubs.repository.ClubRepository;
import io.busata.fourleft.importer.updaters.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClubSyncService {
    private final EventCleanService eventCleanService;
    private final ClubRepository clubRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final RacenetSyncService racenetSyncService;

    @Transactional
    public List<Club> findClubs() {
        return clubRepository.findAll();
    }

    public void cleanArchived() {
        eventCleanService.cleanArchived();
    }

    public void updateClubDetails(Club club) {
         club.getCurrentEvent().ifPresentOrElse(event -> {
            if (event.hasEnded()) {
                log.info("-- Club {} has active event that ended, updating.", club.getName());
                racenetSyncService.fullRefreshClub(club.getReferenceId());

                applicationEventPublisher.publishEvent(new ClubEventEnded(club.getReferenceId()));

                log.info("-- Club {} had active event that ended, checking if new one started", club.getName());
                club.getCurrentEvent().ifPresent(newEvent -> {
                    applicationEventPublisher.publishEvent(new ClubEventStarted(club.getReferenceId()));
                });
            }

        }, () -> {
            if(club.requiresRefresh()) {
                log.info("-- Club {} has no active event, reached refresh threshold, updating.", club.getName());
                racenetSyncService.fullRefreshClub(club.getReferenceId());
            }

            club.getCurrentEvent().ifPresentOrElse(newEvent -> {
                applicationEventPublisher.publishEvent(new ClubEventStarted(club.getReferenceId()));
            }, () -> {
                applicationEventPublisher.publishEvent(new ClubInactive(club.getReferenceId()));
            });
        });
    }

    public void updateClubLeaderboards(Club club) {
        club.getCurrentEvent().ifPresent(event -> {
            if(shouldUpdateLeaderboards(event)) {
                log.info("-- Updating leaderboards for {}", club.getName());
                racenetSyncService.refreshLeaderboards(club.getReferenceId());

                log.info("-- Update done.");

                applicationEventPublisher.publishEvent(new LeaderboardUpdated(club.getReferenceId()));
            }
        });
    }

    private boolean shouldUpdateLeaderboards(Event event) {
        return event.getLastResultCheckedTime() == null ||
                Duration.between(event.getLastResultCheckedTime(), LocalDateTime.now()).toMinutes() >= 10;
    }

    public Club getOrCreate(long clubId) {
        return clubRepository.findByReferenceId(clubId)
                .orElseGet(
                        () -> {
                            log.info("Unknown club {}, fetching from API", clubId);
                            racenetSyncService.createClub(clubId);
                            racenetSyncService.fullRefreshClub(clubId);
                            return clubRepository.getByReferenceId(clubId);
                        }
                );
    }
}
