package com.zeylin.eventsourcingwithcqrs.commands;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateShowtimeCommand extends BaseCommand<String> {

    private final LocalDateTime time;

    public UpdateShowtimeCommand(String id, LocalDateTime time) {
        super(id);
        this.time = time;
    }

}
