package com.zeylin.eventsourcingwithcqrs.aggregates;

import com.zeylin.eventsourcingwithcqrs.events.ShowtimeCreatedEvent;
import com.zeylin.eventsourcingwithcqrs.events.ShowtimeUpdatedEvent;
import com.zeylin.eventsourcingwithcqrs.exceptions.NotFoundException;
import com.zeylin.eventsourcingwithcqrs.repositories.ShowtimeAggregateRepository;
import com.zeylin.eventsourcingwithcqrs.utils.annotations.EventHandler;
import com.zeylin.eventsourcingwithcqrs.utils.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ShowtimeAggregateEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowtimeAggregateEventHandler.class);

    private final ShowtimeAggregateRepository showtimeAggregateRepository;

    public ShowtimeAggregateEventHandler(ShowtimeAggregateRepository showtimeAggregateRepository) {
        this.showtimeAggregateRepository = showtimeAggregateRepository;
    }

    @EventHandler(type = EventType.SHOWTIME_CREATE)
    public void on(ShowtimeCreatedEvent event) {
        LOGGER.info("Received event of type {}", EventType.SHOWTIME_CREATE);

        ShowtimeAggregate showtimeAggregate = new ShowtimeAggregate();
        showtimeAggregate.setId(event.getId());
        showtimeAggregate.setMovieId(event.getMovieId());
        showtimeAggregate.setAuditoriumId(event.getAuditoriumId());
        showtimeAggregate.setTime(event.getTime());

        showtimeAggregateRepository.save(showtimeAggregate);
    }

    @EventHandler(type = EventType.SHOWTIME_UPDATE)
    public void on(ShowtimeUpdatedEvent event) throws NotFoundException {
        LOGGER.info("Received event of type {}", EventType.SHOWTIME_UPDATE);

        ShowtimeAggregate showtimeAggregate = showtimeAggregateRepository.findById(event.getId())
                .orElseThrow(() -> new NotFoundException("Showtime not found."));
        showtimeAggregate.setId(event.getId());
        showtimeAggregate.setTime(event.getTime());

        showtimeAggregateRepository.save(showtimeAggregate);
    }

}
