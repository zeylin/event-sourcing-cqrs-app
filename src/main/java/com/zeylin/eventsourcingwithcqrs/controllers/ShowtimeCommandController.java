package com.zeylin.eventsourcingwithcqrs.controllers;

import com.zeylin.eventsourcingwithcqrs.dto.ShowtimeCreateDto;
import com.zeylin.eventsourcingwithcqrs.dto.ShowtimeUpdateDto;
import com.zeylin.eventsourcingwithcqrs.services.ShowtimeCommandService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/showtimes-command")
public class ShowtimeCommandController {

    private final ShowtimeCommandService showtimeCommandService;

    public ShowtimeCommandController(ShowtimeCommandService showtimeCommandService) {
        this.showtimeCommandService = showtimeCommandService;
    }

    @PostMapping
    public String createShowtime(@RequestBody ShowtimeCreateDto showtimeCreateDto) {
        return showtimeCommandService.createShowtime(showtimeCreateDto);
    }

    @PutMapping
    public void updateShowtime(@RequestBody ShowtimeUpdateDto showtimeUpdateDto) {
        showtimeCommandService.updateShowtime(showtimeUpdateDto);
    }

}
