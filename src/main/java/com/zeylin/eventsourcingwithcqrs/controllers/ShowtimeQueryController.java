package com.zeylin.eventsourcingwithcqrs.controllers;

import com.zeylin.eventsourcingwithcqrs.models.EventEntry;
import com.zeylin.eventsourcingwithcqrs.services.ShowtimeQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/showtimes-query")
public class ShowtimeQueryController {

    private final ShowtimeQueryService showtimeQueryService;

    public ShowtimeQueryController(ShowtimeQueryService showtimeQueryService) {
        this.showtimeQueryService = showtimeQueryService;
    }

    @GetMapping("/{id}/events")
    public List<EventEntry> getAllByAggregateId(@PathVariable String id) {
        return showtimeQueryService.listEventsByAggregateId(id);
    }

}
