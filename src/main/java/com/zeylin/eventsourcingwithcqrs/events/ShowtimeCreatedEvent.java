package com.zeylin.eventsourcingwithcqrs.events;

import com.zeylin.eventsourcingwithcqrs.utils.annotations.PayloadElement;
import com.zeylin.eventsourcingwithcqrs.utils.annotations.PayloadSerializable;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@PayloadSerializable
public class ShowtimeCreatedEvent extends BaseEvent<String> {

    @PayloadElement
    private final Integer movieId;
    @PayloadElement
    private final LocalDateTime time;
    @PayloadElement
    private final Integer auditoriumId;

    public ShowtimeCreatedEvent(String id, Integer movieId, LocalDateTime time, Integer auditoriumId) {
        super(id);
        this.movieId = movieId;
        this.time = time;
        this.auditoriumId = auditoriumId;
    }

}
