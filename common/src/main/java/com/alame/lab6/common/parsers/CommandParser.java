package com.alame.lab6.common.parsers;

import com.alame.lab6.common.commands.Command;
import com.alame.lab6.common.commands.CommandMapper;
import com.alame.lab6.common.exceptions.CommandNotFoundException;
import com.alame.lab6.common.exceptions.IncorrectCommandParameterException;

import java.util.Arrays;

/**
 * class for parsing command from string
 */
public class CommandParser {
    private final CommandMapper commandMapper;
    public CommandParser(CommandMapper commandMapper){
        this.commandMapper = commandMapper;
    }
    /**
     * parse command from string
     * @param line string to parse
     * @return command
     * @throws CommandNotFoundException if command doesn't exist
     * @throws IncorrectCommandParameterException if command parameters are not valid
     */
    public Command parseCommand(String line) throws CommandNotFoundException, IncorrectCommandParameterException {
        String[] arguments = line.split(" ");
        if (commandMapper.commandExist(arguments[0])){
            Command command = commandMapper.getNewCommandInstanceByString(arguments[0]);
            command.setParameters(Arrays.copyOfRange(arguments, 1, arguments.length));
            return command;
        }
        else{
            throw new CommandNotFoundException("Такой команды нет");
        }
    }
}
