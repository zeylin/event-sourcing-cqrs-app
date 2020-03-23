package com.zeylin.eventsourcingwithcqrs.services;

import com.zeylin.eventsourcingwithcqrs.commands.CreateShowtimeCommand;
import com.zeylin.eventsourcingwithcqrs.commands.UpdateShowtimeCommand;
import com.zeylin.eventsourcingwithcqrs.dto.ShowtimeCreateDto;
import com.zeylin.eventsourcingwithcqrs.dto.ShowtimeUpdateDto;
import com.zeylin.eventsourcingwithcqrs.utils.ShowtimeCommandDispatcher;
import com.zeylin.eventsourcingwithcqrs.utils.CommandType;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShowtimeCommandServiceImpl implements ShowtimeCommandService {

    private final ShowtimeCommandDispatcher showtimeCommandDispatcher;

    public ShowtimeCommandServiceImpl(ShowtimeCommandDispatcher showtimeCommandDispatcher) {
        this.showtimeCommandDispatcher = showtimeCommandDispatcher;
    }

    @Override
    public String createShowtime(ShowtimeCreateDto dto) {
        String id = UUID.randomUUID().toString();
        CreateShowtimeCommand command = new CreateShowtimeCommand(id, dto.getMovieId(), dto.getTime(), dto.getAuditoriumId());
//        showtimeCommandDispatcher.sendCommand(command, CommandType.CREATE);
        showtimeCommandDispatcher.toggleCommand(command);
        return id;
    }

    @Override
    public void updateShowtime(ShowtimeUpdateDto dto) {
        UpdateShowtimeCommand command = new UpdateShowtimeCommand(dto.getId(), dto.getTime());
//        showtimeCommandDispatcher.sendCommand(command, CommandType.UPDATE);
        showtimeCommandDispatcher.toggleCommand(command);
    }

}
