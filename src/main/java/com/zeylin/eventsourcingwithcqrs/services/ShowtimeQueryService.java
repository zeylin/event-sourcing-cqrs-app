package com.zeylin.eventsourcingwithcqrs.services;

import com.zeylin.eventsourcingwithcqrs.models.EventEntry;

import java.util.List;

/**
 * Fetching showtime queries.
 */
public interface ShowtimeQueryService {
    public List<EventEntry> listEventsByAggregateId(String id);
}
