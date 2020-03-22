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
public class ShowtimeUpdateDto {

    private String id;
    private LocalDateTime time;

}
