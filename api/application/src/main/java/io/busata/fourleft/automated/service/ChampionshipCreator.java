package io.busata.fourleft.automated.service;

import io.busata.fourleft.gateway.racenet.RacenetGateway;
import io.busata.fourleft.importer.updaters.RacenetSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChampionshipCreator {

    private final RacenetGateway client;
    private final DailyChampionshipCreator dailyChampionshipCreator;
    private final WeeklyChampionshipCreator weeklyChampionshipCreator;

    private final RacenetSyncService clubSyncService;

    public void createDailyChampionship(long clubId) {
        log.info("Creating daily championship");

        client.createChampionship(clubId, dailyChampionshipCreator.createEvent(clubId));

        log.info("Championship created!");

        clubSyncService.fullRefreshClub(clubId);
    }

    public void createWeeklyChampionship(long clubId) {
        log.info("Creating monthly championship");

        client.createChampionship(clubId, weeklyChampionshipCreator.createEvent(clubId));

        log.info("Championship created");

        clubSyncService.fullRefreshClub(clubId);
    }
}