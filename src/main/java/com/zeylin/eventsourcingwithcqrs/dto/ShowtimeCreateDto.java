package com.zeylin.eventsourcingwithcqrs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeCreateDto {

    private Integer movieId;
    private LocalDateTime time;
    private Integer auditoriumId;

}
