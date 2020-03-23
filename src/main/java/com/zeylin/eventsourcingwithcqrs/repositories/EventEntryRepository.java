package com.zeylin.eventsourcingwithcqrs.repositories;

import com.zeylin.eventsourcingwithcqrs.models.EventEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventEntryRepository extends CrudRepository<EventEntry, Integer> {

    List<EventEntry> findAllByAggregateId(String id);

}
