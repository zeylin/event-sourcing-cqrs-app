package com.zeylin.eventsourcingwithcqrs.utils;

import com.zeylin.eventsourcingwithcqrs.aggregates.ShowtimeAggregateEventHandler;
import com.zeylin.eventsourcingwithcqrs.events.BaseEvent;
import com.zeylin.eventsourcingwithcqrs.events.ShowtimeCreatedEvent;
import com.zeylin.eventsourcingwithcqrs.events.ShowtimeUpdatedEvent;
import com.zeylin.eventsourcingwithcqrs.services.EventEntryService;
import com.zeylin.eventsourcingwithcqrs.utils.annotations.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class ShowtimeAggregateUtil implements AggregateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowtimeAggregateUtil.class);

    private final EventEntryService eventEntryService;
    private final ShowtimeAggregateEventHandler showtimeAggregateEventHandler;

    public ShowtimeAggregateUtil(EventEntryService eventEntryService, ShowtimeAggregateEventHandler showtimeAggregateEventHandler) {
        this.eventEntryService = eventEntryService;
        this.showtimeAggregateEventHandler = showtimeAggregateEventHandler;
    }

    @Override
    public void applyEvent(BaseEvent event, EventType eventType) {
        LOGGER.info("Applying event of type = {}, event id = {}", eventType, event.getId());

        eventEntryService.saveEvent(event, eventType);

        try {
            toggleEvent(event, eventType);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.error(e.getMessage(), e);
        }

//        try {
//            toggleEvent(event, eventType);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage(), e);
//        }
    }

    private void toggleEvent(BaseEvent event, EventType eventType) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
    InvocationTargetException {
        Method method = ShowtimeAggregateEventHandler.class.getDeclaredMethod("on", event.getClass());

        if (method.isAnnotationPresent(EventHandler.class)) {
            method.setAccessible(true);
            EventType type = method.getAnnotation(EventHandler.class).type();
            if (type == eventType) {
                LOGGER.info("Toggling event with type {}", type);
                method.invoke(showtimeAggregateEventHandler, event);
            }
        }
    }

    public void toggleEvent(ShowtimeCreatedEvent event) {
        LOGGER.info("Applying event of type = {}, event id = {}", EventType.SHOWTIME_CREATE, event.getId());

        eventEntryService.saveEvent(event, EventType.SHOWTIME_CREATE);
        showtimeAggregateEventHandler.on(event);
    }

    public void toggleEvent(ShowtimeUpdatedEvent event) {
        LOGGER.info("Applying event of type = {}, event id = {}", EventType.SHOWTIME_UPDATE, event.getId());

        eventEntryService.saveEvent(event, EventType.SHOWTIME_UPDATE);
        showtimeAggregateEventHandler.on(event);
    }

}
