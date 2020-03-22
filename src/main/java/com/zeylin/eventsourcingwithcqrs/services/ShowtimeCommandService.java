package com.zeylin.eventsourcingwithcqrs.services;

import com.zeylin.eventsourcingwithcqrs.dto.ShowtimeCreateDto;
import com.zeylin.eventsourcingwithcqrs.dto.ShowtimeUpdateDto;

/**
 * Handling commands.
 */
public interface ShowtimeCommandService {
    public String createShowtime(ShowtimeCreateDto showtimeCreateDto);
    public void updateShowtime(ShowtimeUpdateDto showtimeUpdateDto);
}
