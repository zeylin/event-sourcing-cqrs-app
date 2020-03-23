package com.zeylin.eventsourcingwithcqrs.repositories;

import com.zeylin.eventsourcingwithcqrs.aggregates.ShowtimeAggregate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowtimeAggregateRepository extends CrudRepository<ShowtimeAggregate, String> {
}
