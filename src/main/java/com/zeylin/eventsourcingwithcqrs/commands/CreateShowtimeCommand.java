package com.zeylin.eventsourcingwithcqrs.commands;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateShowtimeCommand extends BaseCommand<String> {

    private final Integer movieId;
    private final LocalDateTime time;
    private final Integer auditoriumId;

    public CreateShowtimeCommand(String id, Integer movieId, LocalDateTime time, Integer auditoriumId) {
        super(id);
        this.movieId = movieId;
        this.time = time;
        this.auditoriumId = auditoriumId;
    }

}
