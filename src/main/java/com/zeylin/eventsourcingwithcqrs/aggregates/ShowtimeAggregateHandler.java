package com.zeylin.eventsourcingwithcqrs.aggregates;

import com.zeylin.eventsourcingwithcqrs.commands.CreateShowtimeCommand;
import com.zeylin.eventsourcingwithcqrs.commands.UpdateShowtimeCommand;
import com.zeylin.eventsourcingwithcqrs.events.ShowtimeCreatedEvent;
import com.zeylin.eventsourcingwithcqrs.events.ShowtimeUpdatedEvent;
import com.zeylin.eventsourcingwithcqrs.utils.ShowtimeAggregateUtil;
import com.zeylin.eventsourcingwithcqrs.utils.annotations.CommandHandler;
import com.zeylin.eventsourcingwithcqrs.utils.CommandType;
import com.zeylin.eventsourcingwithcqrs.utils.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ShowtimeAggregateHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowtimeAggregateHandler.class);

    private final ShowtimeAggregateUtil showtimeAggregateUtil;

    public ShowtimeAggregateHandler(ShowtimeAggregateUtil showtimeAggregateUtil) {
        this.showtimeAggregateUtil = showtimeAggregateUtil;
    }

    @CommandHandler(type = CommandType.CREATE)
    public void on(CreateShowtimeCommand command) {
        LOGGER.info("Received showtime command of type {}", CommandType.CREATE);

        ShowtimeCreatedEvent event = new ShowtimeCreatedEvent(command.getId(), command.getMovieId(), command.getTime(), command.getAuditoriumId());
        showtimeAggregateUtil.applyEvent(event, EventType.SHOWTIME_CREATE);
    }

    @CommandHandler(type = CommandType.UPDATE)
    public void on(UpdateShowtimeCommand command) {
        LOGGER.info("Received showtime command of type {}", CommandType.UPDATE);

        ShowtimeUpdatedEvent event = new ShowtimeUpdatedEvent(command.getId(), command.getTime());
        showtimeAggregateUtil.applyEvent(event, EventType.SHOWTIME_UPDATE);
    }

}
