package com.alame.lab6.client.commands;

import com.alame.lab6.common.commands.Command;
import com.alame.lab6.common.exceptions.IncorrectCommandParameterException;

/**
 * command for exit from application
 */
public class ExitCommand implements Command {
    /**
     * shut down the application
     * @return true
     */
    @Override
    public boolean execute() {
        System.exit(0);
        return true;
    }

    /**
     * set no parameters
     * @param parameters - all parameters of command
     * @throws IncorrectCommandParameterException if parameters size!=0
     */
    @Override
    public void setParameters(String[] parameters) throws IncorrectCommandParameterException {
        if (parameters.length!=0) throw new IncorrectCommandParameterException("Данная команда не принимает аргументов");
    }

    /**
     * @return command description
     */
    @Override
    public String description() {
        return "exit: завершает работу программы";
    }
    /**
     * @return command name
     */
    @Override
    public String name() {
        return "exit";
    }

    /**
     * create new ExitCommand
     * @return new ExitCommand
     */
    @Override
    public Command newInstance() {
        return new ExitCommand();
    }
}
