package com.zeylin.eventsourcingwithcqrs.aggregates;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ShowtimeAggregate {

    private String id;
    private Integer movieId;
    private LocalDateTime time;
    private Integer auditoriumId;

}
