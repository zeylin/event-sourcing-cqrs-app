package com.zeylin.eventsourcingwithcqrs.events;

import com.zeylin.eventsourcingwithcqrs.utils.annotations.PayloadElement;
import com.zeylin.eventsourcingwithcqrs.utils.annotations.PayloadSerializable;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@PayloadSerializable
public class ShowtimeUpdatedEvent extends BaseEvent<String> {

    @PayloadElement
    private final LocalDateTime time;

    public ShowtimeUpdatedEvent(String id, LocalDateTime time) {
        super(id);
        this.time = time;
    }

}
