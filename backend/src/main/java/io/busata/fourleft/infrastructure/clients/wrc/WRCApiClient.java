package io.busata.fourleft.infrastructure.clients.wrc;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="wrc", url = "https://api.wrc.com/sdb")
public interface WRCApiClient {
    @GetMapping("/rallyevent/{rallyEventId}/contelPageId/310774/ticker-entries")
    WRCTickerSummaryTo getTickerSummary(@PathVariable String rallyEventId);
}