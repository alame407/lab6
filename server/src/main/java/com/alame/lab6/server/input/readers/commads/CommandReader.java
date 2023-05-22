package com.alame.lab6.server.input.readers.commads;

import com.alame.lab6.common.commands.Command;
import com.alame.lab6.common.exceptions.CommandNotFoundException;
import com.alame.lab6.common.exceptions.IncorrectCommandParameterException;

/**
 * interface for all class that read commands
 */
public interface CommandReader {
    /**
     * read command
     * @return received command
     * @throws IncorrectCommandParameterException if command parameters are not valid
     */
    Command readCommand() throws IncorrectCommandParameterException, CommandNotFoundException;
}