package com.alame.lab6.server.commands;

import com.alame.lab6.common.commands.Command;
import com.alame.lab6.common.executors.Executor;

/**
 * class for handle commands
 */
public class CommandHandler {
    private final Executor executor;
    public CommandHandler(Executor executor){
        this.executor = executor;
    }

    /**
     * handle given command
     * @param command - command to handle
     */
    public void handle(Command command){
        executor.setCommand(command);
        executor.executeCommand();
    }
}
