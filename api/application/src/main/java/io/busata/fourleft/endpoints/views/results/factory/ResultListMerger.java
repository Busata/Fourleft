package io.busata.fourleft.endpoints.views.results.factory;

import io.busata.fourleft.api.models.DriverEntryTo;
import io.busata.fourleft.api.models.DriverResultTo;
import io.busata.fourleft.api.models.VehicleEntryTo;
import io.busata.fourleft.api.models.views.ActivityInfoTo;
import io.busata.fourleft.api.models.views.ResultListTo;
import io.busata.fourleft.common.StageTimeParser;
import io.busata.fourleft.helpers.Factory;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Factory
@RequiredArgsConstructor
public class ResultListMerger {

    private final StageTimeParser parser;
    private final DriverEntryToFactory driverEntryToFactory;

    public ResultListTo mergeResults(List<ResultListTo> results) {
        HashMap<String, DriverResultTo> driverEntries = new HashMap<>();
        HashMap<String, Long> driverEntryCount = new HashMap<>();

        results.stream().map(ResultListTo::results).
                forEach(entries -> {
                    entries.stream().map(DriverEntryTo::result).forEach(entry -> {
                        driverEntryCount.computeIfPresent(entry.racenet(), (key, existingCount) -> existingCount + 1);
                        driverEntryCount.putIfAbsent(entry.racenet(), 1L);

                        driverEntries.computeIfPresent(entry.racenet(), (key, existingDriverEntry) -> mergeEntries(entry, existingDriverEntry));
                        driverEntries.putIfAbsent(entry.racenet(), entry);

                    });
                });


        List<DriverResultTo> entriesOccuringInEveryList = driverEntries.values().stream().filter(
                entry -> driverEntryCount.get(entry.racenet()) == results.size()
        ).collect(Collectors.toList());

        List<DriverEntryTo> driverResultTos = driverEntryToFactory.calculateRelativeData(new ArrayList<>(entriesOccuringInEveryList));

        List<ActivityInfoTo> mergedActivities = results.stream().flatMap(list -> list.activityInfoTo().stream()).distinct().toList();

        return new ResultListTo("", mergedActivities, results.size(), driverResultTos.stream().sorted(Comparator.comparing(DriverEntryTo::activityRank)).collect(Collectors.toList()));
    }
    public DriverResultTo mergeEntries(DriverResultTo entryA, DriverResultTo entryB) {
        Duration activityTotalTime = parser.createDuration(entryA.activityTotalTime()).plus(parser.createDuration(entryB.activityTotalTime()));
        String activityTotalMerged = parser.formatStageTime(activityTotalTime);

        Duration powerStageTotalTime = parser.createDuration(entryA.powerStageTotalTime()).plus(parser.createDuration(entryB.powerStageTotalTime()));
        String powerStageMerged = parser.formatStageTime(powerStageTotalTime);

        return new DriverResultTo(
                entryA.racenet(),
                entryA.nationality(),
                entryA.platform(),
                activityTotalMerged,
                powerStageMerged,
                entryA.isDnf() && entryB.isDnf(),
                mergeVehicleEntries(entryA, entryB)
        );
    }

    private List<VehicleEntryTo> mergeVehicleEntries(DriverResultTo entryA, DriverResultTo entryB) {
        return Stream.concat(entryA.vehicles().stream(), entryB.vehicles().stream()).collect(Collectors.toList());
    }

}