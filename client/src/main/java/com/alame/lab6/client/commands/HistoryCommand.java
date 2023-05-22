package com.alame.lab6.client.commands;

import com.alame.lab6.common.commands.Command;
import com.alame.lab6.common.exceptions.IncorrectCommandParameterException;
import com.alame.lab6.common.printers.Printer;

/**
 * command for showing last names of commands
 */
public class HistoryCommand implements Command {
    private final Printer printer;
    private final CommandHandler commandHandler;
    public HistoryCommand(CommandHandler commandHandler, Printer printer){
        this.commandHandler = commandHandler;
        this.printer = printer;
    }

    /**
     * show name of last commands
     * @return true
     */
    @Override
    public boolean execute() {
        printer.printlnObject(commandHandler.getHistory());
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
        return "history: выводит 15 последних команд";
    }
    /**
     * @return command name
     */
    @Override
    public String name() {
        return "history";
    }

    /**
     * create new HistoryCommand
     * @return new HistoryCommand with same commandHandler
     */
    @Override
    public Command newInstance() {
        return new HistoryCommand(commandHandler, printer);
    }
}
