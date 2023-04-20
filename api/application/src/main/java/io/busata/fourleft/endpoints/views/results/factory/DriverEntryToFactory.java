package io.busata.fourleft.endpoints.views.results.factory;

import io.busata.fourleft.api.models.DriverEntryTo;
import io.busata.fourleft.api.models.DriverRelativeResultTo;
import io.busata.fourleft.api.models.DriverResultTo;
import io.busata.fourleft.api.models.VehicleEntryTo;
import io.busata.fourleft.common.StageTimeParser;
import io.busata.fourleft.domain.clubs.models.BoardEntry;
import io.busata.fourleft.domain.clubs.models.Event;
import io.busata.fourleft.domain.clubs.models.Stage;
import io.busata.fourleft.domain.clubs.repository.LeaderboardRepository;
import io.busata.fourleft.domain.configuration.event_restrictions.models.ViewEventRestrictions;
import io.busata.fourleft.domain.configuration.event_restrictions.repository.ViewEventRestrictionsRepository;
import io.busata.fourleft.domain.configuration.player_restrictions.PlayerFilterType;
import io.busata.fourleft.domain.configuration.results_views.SingleClubView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.commons.collections4.IterableUtils.first;


@Component
@RequiredArgsConstructor
public class DriverEntryToFactory {

    private final StageTimeParser stageTimeParser;
    private final PlatformToFactory platformToFactory;
    private final LeaderboardRepository leaderboardRepository;
    private final ViewEventRestrictionsRepository restrictionsRepository;

    public FilteredEntryList<DriverEntryTo> create(SingleClubView view, Event event) {
        Stream<DriverResultTo> stream = getEntries(event, event.getLastStage()).stream()
                .map(this::createDriverResult);

        Optional<ViewEventRestrictions> eventRestrictions = restrictionsRepository.findByResultsViewIdAndChallengeIdAndEventId(view.getId(), event.getChallengeId(), event.getReferenceId());
        if (eventRestrictions.isPresent()) {
            ViewEventRestrictions restrictions = eventRestrictions.get();
            stream = stream.map(entry -> applyRestrictionFilter(entry, restrictions));
        }

        List<DriverResultTo> driverResults = stream.collect(Collectors.toList());

        driverResults = mergePowerStageEntries(driverResults, view, event);

        if (view.getPlayerFilter().getFilterType() == PlayerFilterType.INCLUDE) {
            driverResults = includeNames(driverResults, view.getPlayerFilter().getRacenetNames());
        }

        int totalEntries = driverResults.size();

        List<DriverEntryTo> driverEntryTos = calculateRelativeData(driverResults);

        if (view.getPlayerFilter().getFilterType() == PlayerFilterType.FILTER) {
            driverEntryTos = filterNames(driverEntryTos, view.getPlayerFilter().getRacenetNames());
        }

        return new FilteredEntryList<>(driverEntryTos, totalEntries);
    }

    private DriverResultTo applyRestrictionFilter(DriverResultTo entry, ViewEventRestrictions viewEventRestrictions) {
        return entry.toBuilder()
                .vehicles(entry.vehicles().stream()
                        .map(existingEntry -> {
                            return new VehicleEntryTo(
                                    existingEntry.vehicleName(),
                                    viewEventRestrictions.isValidVehicle(existingEntry.vehicleName())
                            );
                        }).collect(Collectors.toList())).build();
    }


    public List<DriverEntryTo> calculateRelativeData(List<DriverResultTo> entries) {
        if (entries.isEmpty()) {
            return List.of();
        }

        final var sortedByTotalTime = stageTimeParser.sortedByTime(entries, DriverResultTo::activityTotalTime);
        final var sortedByPowerStageTime = stageTimeParser.sortedByTime(entries, DriverResultTo::powerStageTotalTime);

        final var fastestTotalTime = first(sortedByTotalTime).activityTotalTime();
        final var fastestPowerStageTime = first(sortedByPowerStageTime).powerStageTotalTime();


        return entries.stream().map(entry ->
                {
                    DriverRelativeResultTo driverRelativeResultTo = new DriverRelativeResultTo(
                            sortedByTotalTime.indexOf(entry) + 1L,
                            stageTimeParser.getDiff(fastestTotalTime, entry.activityTotalTime()),
                            sortedByPowerStageTime.indexOf(entry) + 1L,
                            stageTimeParser.getDiff(fastestPowerStageTime, entry.powerStageTotalTime())
                    );

                    return new DriverEntryTo(
                            entry,
                            driverRelativeResultTo
                    );
                }
        ).toList();
    }

    private List<DriverResultTo> mergePowerStageEntries(List<DriverResultTo> entries, SingleClubView view, Event event) {
        final var powerStageEntries = getPowerStageEntries(view, event);

        return entries.stream().map(entry -> {
            var stageTime = Duration.ZERO;

            final var userPowerStageEntries = powerStageEntries.stream().filter(powerStageEntry -> powerStageEntry.racenet().equalsIgnoreCase(entry.racenet())).toList();

            for (DriverResultTo userPowerStageEntry : userPowerStageEntries) {
                stageTime = stageTime.plus(stageTimeParser.createDuration(userPowerStageEntry.powerStageTotalTime()));
            }

            return new DriverResultTo(
                    entry.racenet(),
                    entry.nationality(),
                    entry.platform(),
                    entry.activityTotalTime(),
                    stageTimeParser.formatStageDiff(stageTime),
                    entry.isDnf(),
                    entry.vehicles()
            );

        }).collect(Collectors.toList());
    }

    private DriverResultTo createDriverResult(BoardEntry entry) {
        return new DriverResultTo(
                entry.getName(),
                entry.getNationality(),
                platformToFactory.createFromRacenet(entry.getName()),
                entry.getTotalTime(),
                entry.getStageTime(),
                entry.isDnf(),
                List.of(new VehicleEntryTo(entry.getVehicleName(), true))
        );
    }


    private List<DriverResultTo> getPowerStageEntries(SingleClubView view, Event event) {
        var stages = event.getStages();
        return view.getPowerStageIndices().stream()
                .map(index -> index >= 0 ? index : view.getPowerStageIndices().size() + index)
                .map(stages::get)
                .map(stage -> getEntries(event, stage))
                .flatMap(Collection::stream)
                .map(this::createDriverResult)
                .collect(Collectors.toList());
    }

    private List<BoardEntry> getEntries(Event event, Stage stage) {
        return leaderboardRepository.findLeaderboardByChallengeIdAndEventIdAndStageId(event.getChallengeId(), event.getReferenceId(), String.valueOf(stage.getReferenceId()))
                .stream().
                flatMap(board -> board.getEntries().stream())
                .toList();
    }

    private List<DriverResultTo> includeNames(List<DriverResultTo> entries, List<String> players) {
        List<String> sanitized = players.stream().map(String::toLowerCase).toList();

        return entries.stream().filter(entry -> sanitized.contains(entry.racenet().toLowerCase())).toList();
    }

    private List<DriverEntryTo> filterNames(List<DriverEntryTo> entries, List<String> players) {
        List<String> sanitized = players.stream().map(String::toLowerCase).toList();

        return entries.stream().filter(entry -> sanitized.contains(entry.racenet().toLowerCase())).toList();
    }
}