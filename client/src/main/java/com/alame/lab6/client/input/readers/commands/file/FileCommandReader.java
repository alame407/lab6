package com.alame.lab6.client.input.readers.commands.file;


import com.alame.lab6.common.commands.Command;
import com.alame.lab6.client.input.readers.FileReader;
import com.alame.lab6.client.input.readers.commands.CommandReader;
import com.alame.lab6.common.parsers.CommandParser;
import com.alame.lab6.common.exceptions.CommandNotFoundException;
import com.alame.lab6.common.exceptions.IncorrectCommandParameterException;

import java.io.IOException;

/**
 * class for reading command from file
 */
public class FileCommandReader implements CommandReader {
    /**
     * field that realise reading from file
     */
    private final FileReader fileReader;
    private final CommandParser commandParser;
    public FileCommandReader(FileReader fileReader, CommandParser commandParser) {
        this.fileReader = fileReader;
        this.commandParser = commandParser;
    }

    /**
     * read command from file
     * @return received command
     * @throws IncorrectCommandParameterException if command parameters are not valid or command doesn't exist
     */
    @Override
    public Command readCommand() throws IncorrectCommandParameterException, CommandNotFoundException {
        try{
            return commandParser.parseCommand(fileReader.getNextLine());
        } catch (IOException e) {
            throw new IncorrectCommandParameterException(e.getMessage());
        }
    }
}
