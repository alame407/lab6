package com.alame.lab6.common.executors;


import com.alame.lab6.common.commands.Command;

/**
 * class for command execution
 */
public class Executor {
    /**
     * current command
     */
    private Command command;

    /**
     * set current command
     * @param command - current command
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * execute current command
     */
    public boolean executeCommand(){
        return command.execute();
    }
}
