package io.busata.fourleft.wrc.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WRCTickerEntryRepository extends JpaRepository<WRCTickerEntry, UUID> {
    Long countByEventId(String eventId);

    List<WRCTickerEntry> findByEventId(String eventId);

}