package com.alame.lab6.client.commands;


import com.alame.lab6.common.executors.Executor;
import com.alame.lab6.client.utility.structers.LimitedQueue;
import com.alame.lab6.common.commands.Command;

import java.util.Queue;

/**
 * class for processing commands and saving them to history
 */
public class CommandHandler {
    /**
     * size of the stored history of the command
     */
    private final static int HISTORY_SIZE = 12;
    /**
     * field that store history of last commands
     */
    private final Queue<String> history = new LimitedQueue<>(HISTORY_SIZE);
    /**
     * field that executes the command
     */
    private final Executor executor = new Executor();

    public CommandHandler(){}

    /**
     * handle given command
     * @param command - command to handle
     */
    public void handle(Command command){
        executor.setCommand(command);
        if(executor.executeCommand()) {
            addCommandToHistory(command);
        }
    }

    /**
     * add given command to history
     * @param command - command to add
     */
    private void addCommandToHistory(Command command){
        history.add(command.name());
    }

    /**
     * @return history of last commands
     */
    public Queue<String> getHistory() {
        return history;
    }
}
