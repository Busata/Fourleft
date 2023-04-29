package io.busata.fourleft.endpoints.views.summary;

import io.busata.fourleft.api.Routes;
import io.busata.fourleft.api.models.views.ViewEventSummaryTo;
import io.busata.fourleft.domain.views.configuration.ClubViewRepository;
import io.busata.fourleft.endpoints.views.summary.factory.ViewEventSummaryToFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ViewSummaryEndpoint {
    private final ClubViewRepository repository;
    private final ViewEventSummaryToFactory viewEventSummaryToFactory;


    @GetMapping(Routes.CLUB_VIEWS_EVENT_SUMMARY_BY_VIEW_ID)
    public ViewEventSummaryTo getEventSummary(@PathVariable UUID viewId) {
        final var clubView = repository.findById(viewId).orElseThrow();
        return viewEventSummaryToFactory.create(clubView);
    }

}
