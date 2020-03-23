package com.zeylin.eventsourcingwithcqrs.utils;

import com.zeylin.eventsourcingwithcqrs.aggregates.ShowtimeAggregateHandler;
import com.zeylin.eventsourcingwithcqrs.commands.BaseCommand;
import com.zeylin.eventsourcingwithcqrs.commands.CreateShowtimeCommand;
import com.zeylin.eventsourcingwithcqrs.commands.UpdateShowtimeCommand;
import com.zeylin.eventsourcingwithcqrs.utils.annotations.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class ShowtimeCommandDispatcher implements CommandDispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowtimeCommandDispatcher.class);

    private final ShowtimeAggregateHandler showtimeAggregateHandler;

    public ShowtimeCommandDispatcher(ShowtimeAggregateHandler showtimeAggregateHandler) {
        this.showtimeAggregateHandler = showtimeAggregateHandler;
    }

    @Override
    public void sendCommand(BaseCommand command, CommandType commandType) {
        LOGGER.info("Sending command with type = {}, command id = {}", commandType, command.getId());

        try {
            toggleCommand(command, commandType);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.error(e.getMessage(), e);
        }

//        try {
//            toggleCommand(command, commandType);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage(), e);
//        }
    }

    private void toggleCommand(BaseCommand command, CommandType commandType) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        Method method = ShowtimeAggregateHandler.class.getDeclaredMethod("on", command.getClass());

        if (method.isAnnotationPresent(CommandHandler.class)) {
            method.setAccessible(true);
            CommandType type = method.getAnnotation(CommandHandler.class).type();
            if (type == commandType) {
                LOGGER.info("Toggling command with type {}", type);
                method.invoke(showtimeAggregateHandler, command);
            }
        }
    }

    public void toggleCommand(CreateShowtimeCommand command) {
        LOGGER.info("Sending command with type = {}, command id = {}", CommandType.CREATE, command.getId());
        showtimeAggregateHandler.on(command);
    }

    public void toggleCommand(UpdateShowtimeCommand command) {
        LOGGER.info("Sending command with type = {}, command id = {}", CommandType.UPDATE, command.getId());
        showtimeAggregateHandler.on(command);
    }


}
