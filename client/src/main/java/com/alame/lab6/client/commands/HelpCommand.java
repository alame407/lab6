package com.alame.lab6.client.commands;

import com.alame.lab6.common.commands.Command;
import com.alame.lab6.common.commands.CommandMapper;
import com.alame.lab6.common.exceptions.IncorrectCommandParameterException;
import com.alame.lab6.common.printers.Printer;

/**
 * command for showing all commands and their descriptions
 */
public class HelpCommand implements Command {
    private final Printer printer;
    private final CommandMapper commandMapper;
    public HelpCommand(Printer printer, CommandMapper commandMapper){
        this.printer = printer;
        this.commandMapper = commandMapper;
    }

    /**
     * show all command and their descriptions
     * @return true
     */
    @Override
    public boolean execute() {
        for(Command command: commandMapper.getAllCommands()){
            printer.printlnString(command.description());
        }
        return true;
    }

    /**
     * set no parameters
     * @param parameters - all parameters of command
     * @throws IncorrectCommandParameterException if parameter size!=0
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
        return "help: выводит справку о командах";
    }
    /**
     * @return command name
     */
    @Override
    public String name() {
        return "help";
    }

    /**
     * create new HelpCommand
     * @return new HelpCommand
     */
    @Override
    public Command newInstance() {
        return new HelpCommand(printer, commandMapper);
    }
}
