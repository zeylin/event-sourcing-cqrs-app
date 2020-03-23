package com.zeylin.eventsourcingwithcqrs.utils;

import com.zeylin.eventsourcingwithcqrs.events.BaseEvent;

/**
 * Aggregate utility.
 */
public interface AggregateUtil {

    void applyEvent(BaseEvent event, EventType eventType);

}
