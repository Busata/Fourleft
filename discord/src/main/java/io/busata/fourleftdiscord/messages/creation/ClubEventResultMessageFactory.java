package io.busata.fourleftdiscord.messages.creation;

import discord4j.core.spec.EmbedCreateFields;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import io.busata.fourleft.api.models.DriverEntryTo;
import io.busata.fourleft.api.models.views.ActivityInfoTo;
import io.busata.fourleft.api.models.views.ResultListTo;
import io.busata.fourleft.api.models.views.VehicleTo;
import io.busata.fourleft.api.models.views.ViewPropertiesTo;
import io.busata.fourleft.api.models.views.ViewResultTo;
import io.busata.fourleftdiscord.fieldmapper.DR2FieldMapper;
import io.busata.fourleftdiscord.helpers.ListHelpers;
import io.busata.fourleftdiscord.messages.templates.MessageTemplate;
import io.busata.fourleftdiscord.messages.templates.ResultEntryTemplateResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

import static io.busata.fourleftdiscord.messages.templates.MessageTemplate.messageTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClubEventResultMessageFactory {
    private final DR2FieldMapper fieldMapper;
    private final ResultEntryTemplateResolver templateResolver;

    MessageTemplate NORMAL_TEMPLATE = messageTemplate(
                    "**${rank}** • **${nationalityEmoticon}** • **${name}** • ${totalTime} *(${totalDiff})*");

    MessageTemplate RANKED_BADGE_TEMPLATE =  messageTemplate(
                    "*${badgeRank}* **${rank}** • **${nationalityEmoticon}** • **${name}** • ${totalTime} *(${totalDiff})*");

    MessageTemplate META_NORMAL_TEMPLATE =  messageTemplate(
                    "**${rank}** • **${name}** • ${platform} • ${controllerType} • *${vehicle}*");
    MessageTemplate META_RANKED_BADGE_TEMPLATE =  messageTemplate(
            "**${rank}** • **${name}** • ${platform} • ${controllerType} • *${vehicle}*");

    public List<EmbedCreateSpec> createDefault(ViewResultTo clubResultTo) {
        final var template = getTemplate(clubResultTo.getViewPropertiesTo());
        return create(clubResultTo, template, false);
    }
    public List<EmbedCreateSpec> createMetadata(ViewResultTo clubResultTo) {
        final var template = getMetaDataTemplate(clubResultTo.getViewPropertiesTo());
        return create(clubResultTo, template, false);
    }

    public List<EmbedCreateSpec> createPowerStage(ViewResultTo clubResultTo) {
        final var template = getTemplate(clubResultTo.getViewPropertiesTo());
        return create(clubResultTo, template, true);
    }

    private MessageTemplate getTemplate(ViewPropertiesTo properties) {
        return switch (properties.badgeType()) {
            case NONE -> NORMAL_TEMPLATE;
            case PERCENTAGE -> NORMAL_TEMPLATE;
            case RANKED -> RANKED_BADGE_TEMPLATE;
        };
    }
    private MessageTemplate getMetaDataTemplate(ViewPropertiesTo properties) {
        return switch (properties.badgeType()) {
            case NONE -> META_NORMAL_TEMPLATE;
            case PERCENTAGE -> NORMAL_TEMPLATE;
            case RANKED -> META_RANKED_BADGE_TEMPLATE;
        };
    }

    protected List<EmbedCreateSpec> create(ViewResultTo clubResult, MessageTemplate entryTemplate, boolean powerstageOnly) {
        List<EmbedCreateSpec> specs = new ArrayList<>();

        var builder = EmbedCreateSpec.builder();

        final var eventInfos = clubResult.getMultiListResults().stream().map(ResultListTo::activityInfoTo).flatMap(Collection::stream).collect(Collectors.toList());

        String country = eventInfos.stream().map(ActivityInfoTo::country).distinct().map(fieldMapper::createEmoticon).collect(Collectors.joining(" "));
        String vehicleClass = eventInfos.stream().map(ActivityInfoTo::vehicleClass).distinct().map(fieldMapper::createHumanReadable).collect(Collectors.joining(" "));

        List<String> stageNames = eventInfos.stream().findFirst().stream().flatMap(eventInfoTo -> eventInfoTo.stageNames().stream()).collect(Collectors.toList());

        builder.title("**Results**");
        builder.color(Color.of(244, 0, 75));

        builder.addField("Country", "%s".formatted(country), true);
        builder.addField("Car", vehicleClass, true);
        builder.addField( stageNames.size() > 0 ? "Stages" : "Stage", String.join(", ", stageNames), true);
        builder.footer(EmbedCreateFields.Footer.of("\u2800".repeat(40), null));

        if(clubResult.getMultiListResults().size() > 1) {
            specs.add(builder.build());
            builder = createFullWidthBuilder();
        }


        List<ResultListTo> multiListResults = clubResult.getMultiListResults();
        int totalEntries = clubResult.getMultiListResults().stream().mapToInt(ResultListTo::totalUniqueEntries).sum();

        for (int i = 0; i < multiListResults.size(); i++) {
            ResultListTo resultList = multiListResults.get(i);

            if(resultList.results().size() == 0 && multiListResults.size() > 1) {
                builder.description("*No entries yet*");
            }

            if (StringUtils.isNotBlank(resultList.name())) {
                builder.addField("**%s**", "%s".formatted(getVehicleRestrictions(resultList)), false);
            }

            if (clubResult.getViewPropertiesTo().powerStage()) {
                addPowerStageField(resultList, builder);
            }

            if (!powerstageOnly) {
                final var sortedEntries = resultList.results().stream().sorted(Comparator.comparing(DriverEntryTo::activityRank)).limit(50).collect(Collectors.toList());


                int groupSize = 10;
                var groupedEntries = ListHelpers.partitionInGroups(sortedEntries, groupSize);

                //Temporary fix to avoid field sizes being too big
                boolean reduceEntries = groupedEntries.stream().map(ge -> ge.stream().map(entry -> templateResolver.resolve(entryTemplate, entry)).collect(Collectors.joining("\n"))).map(String::length).anyMatch(entrySize -> entrySize >= 1024);

                if(reduceEntries) {
                    groupSize = 5;
                    groupedEntries = ListHelpers.partitionInGroups(sortedEntries, groupSize);
                }

                int bound = groupedEntries.size();
                for (int groupIdx = 0; groupIdx < bound; groupIdx++) {
                    final var group = groupedEntries.get(groupIdx);
                    String collect = group.stream().map(entry -> templateResolver.resolve(entryTemplate, entry)).collect(Collectors.joining("\n"));
                    builder.addField(determineHeader(groupIdx, groupSize), collect, false);
                }
            }



            if(i == multiListResults.size() - 1) {
                builder.addField("**Last update**", "*%s*".formatted(new PrettyTime().format(resultList.activityInfoTo().stream().findAny().orElseThrow().lastUpdate())), true);
                if(totalEntries > 0) {
                    builder.addField("**Total entries**", "*%s*".formatted(totalEntries), true);
                }
                builder.addField("**Event ending**", "<t:%s:R>".formatted(resultList.activityInfoTo().stream().findAny().orElseThrow().endTime().toInstant().atZone(ZoneOffset.UTC).toEpochSecond()), true);
            }

            specs.add(builder.build());
            builder = createFullWidthBuilder();
        }

        return specs;
    }

    private static String getVehicleRestrictions(ResultListTo resultList) {
        String vehicleRestrictions = resultList.activityInfoTo().stream().flatMap(activityInfoTo -> activityInfoTo.restrictions().getRestrictedVehicles().stream())
                .map(VehicleTo::displayName).distinct().collect(Collectors.joining(" • "));
        if(StringUtils.isBlank(vehicleRestrictions)) {
            return "\u200B";
        } else {
            return vehicleRestrictions;
        }
    }

    private static EmbedCreateSpec.Builder createFullWidthBuilder() {
        return EmbedCreateSpec.builder()
                .color(Color.of(new Random().nextFloat(),new Random().nextFloat(),new Random().nextFloat()));
    }

    private void addPowerStageField(ResultListTo clubResult, EmbedCreateSpec.Builder builder) {
        final var powerstageEntries = clubResult.results().stream().sorted(Comparator.comparing(DriverEntryTo::powerStageRank)).limit(5).collect(Collectors.toList());

        if(powerstageEntries.size() > 0) {
            StringJoiner joiner = new StringJoiner("\n");
            for (int i = 0; i < powerstageEntries.size(); i++) {
                DriverEntryTo powerstageEntry = powerstageEntries.get(i);
                String format = String.format("%s **%s** • **%s** • **%s** • %s *(%s)*",
                        ":rocket:",
                        i + 1,
                        fieldMapper.createEmoticon(powerstageEntry.nationality()),
                        powerstageEntry.racenet(),
                        powerstageEntry.powerStageTotalTime(),
                        powerstageEntry.powerStageTotalDiff()
                );
                joiner.add(format);
            }
            String powerStageNames = clubResult.activityInfoTo().stream().map(activityInfoTo -> activityInfoTo.stageNames().get(activityInfoTo.stageNames().size() - 1)).collect(Collectors.joining(" "));
            builder.addField("Powerstage *(%s)*".formatted(powerStageNames), joiner.toString(), false);
        }
    }
    private String determineHeader(int idx, int groupSize) {
        if (idx == 0) {
            return "Top %s".formatted(groupSize);
        } else {
            var startBound = (idx * groupSize) + 1;
            var endBound = (idx * groupSize) + groupSize;

            return "Top %s - %s".formatted(startBound, endBound);
        }
    }

}