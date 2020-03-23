package com.zeylin.eventsourcingwithcqrs.services;

import com.zeylin.eventsourcingwithcqrs.events.BaseEvent;
import com.zeylin.eventsourcingwithcqrs.exceptions.PayloadSerializationException;
import com.zeylin.eventsourcingwithcqrs.models.EventEntry;
import com.zeylin.eventsourcingwithcqrs.repositories.EventEntryRepository;
import com.zeylin.eventsourcingwithcqrs.utils.ObjectToPayloadConverter;
import com.zeylin.eventsourcingwithcqrs.utils.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventEntryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventEntryService.class);

    private final EventEntryRepository eventEntryRepository;
    private final ObjectToPayloadConverter converter;

    public EventEntryService(EventEntryRepository eventEntryRepository,
                             ObjectToPayloadConverter converter) {
        this.eventEntryRepository = eventEntryRepository;
        this.converter = converter;
    }

    public void saveEvent(BaseEvent event, EventType eventType) {
        EventEntry eventEntry = new EventEntry();
        eventEntry.setAggregateId((String) event.getId());
        eventEntry.setTimestamp(LocalDateTime.now());
        eventEntry.setEventType(eventType);

        try {
            String payload = converter.convertToJsonPayload(event);
            eventEntry.setPayload(payload);
        } catch (PayloadSerializationException e) {
            LOGGER.error(e.getMessage(), e);
        }

        eventEntryRepository.save(eventEntry);
    }

}
