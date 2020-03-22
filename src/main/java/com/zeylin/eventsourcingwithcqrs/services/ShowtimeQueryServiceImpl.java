package com.zeylin.eventsourcingwithcqrs.services;

import com.zeylin.eventsourcingwithcqrs.models.EventEntry;
import com.zeylin.eventsourcingwithcqrs.repositories.EventEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowtimeQueryServiceImpl implements ShowtimeQueryService {

    private final EventEntryRepository eventEntryRepository;

    public ShowtimeQueryServiceImpl(EventEntryRepository eventEntryRepository) {
        this.eventEntryRepository = eventEntryRepository;
    }

    @Override
    public List<EventEntry> listEventsByAggregateId(String id) {
        List<EventEntry> events = eventEntryRepository.findAllByAggregateId(id);
        return events;
    }

//    @Override
//    public List<Object> listAllShowtimes() {
//        return null;
//    }
//
//    @Override
//    public List<Object> listShowtimes(LocalDate date) {
//        return null;
//    }

}
