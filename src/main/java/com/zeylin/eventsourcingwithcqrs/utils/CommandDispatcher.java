package com.zeylin.eventsourcingwithcqrs.utils;

import com.zeylin.eventsourcingwithcqrs.commands.BaseCommand;

/**
 * Dispatch commands.
 */
public interface CommandDispatcher {

    void sendCommand(BaseCommand command, CommandType commandType);

}
