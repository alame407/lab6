package com.alame.lab6.client.input.readers.commands.console;


import com.alame.lab6.common.commands.Command;
import com.alame.lab6.client.input.readers.ConsoleReader;
import com.alame.lab6.client.input.readers.commands.CommandReader;
import com.alame.lab6.common.parsers.CommandParser;
import com.alame.lab6.common.exceptions.CommandNotFoundException;
import com.alame.lab6.common.exceptions.IncorrectCommandParameterException;
import com.alame.lab6.common.printers.Printer;

/**
 * class for reading command from console
 */
public class ConsoleCommandReader extends ConsoleReader implements CommandReader {
    private final Printer printer;
    private final CommandParser commandParser;
    public ConsoleCommandReader(Printer printer, CommandParser commandParser){
        this.printer = printer;
        this.commandParser = commandParser;
    }

    /**
     * read command from console
     * @return received command
     */
    public Command readCommand(){
        printer.printString("Введите команду ");
        while(true) {

            String nextLine = getNextLine();
            try {
                return commandParser.parseCommand(nextLine);
            } catch (CommandNotFoundException | IncorrectCommandParameterException e) {
                printer.printString(e.getMessage() + ", повторите ввод снова ");
            }

        }
    }
}
