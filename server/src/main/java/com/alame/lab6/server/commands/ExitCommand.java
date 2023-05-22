package com.alame.lab6.server.commands;
import com.alame.lab6.common.commands.Command;
import com.alame.lab6.common.exceptions.IncorrectCommandParameterException;
import com.alame.lab6.common.printers.Printer;
import com.alame.lab6.server.servers.ServerInterface;

import java.io.IOException;

/**
 * command for exit from application
 */
public class ExitCommand implements Command {
    private final ServerInterface server;
    private final Printer printer;
    public ExitCommand(ServerInterface server, Printer printer){
        this.server = server;
        this.printer = printer;
    }

    /**
     * shut down the application
     * @return true
     */
    @Override
    public boolean execute() {
        try {
            server.save();
        }
        catch (IOException e){
            printer.printlnString(e.getMessage()+" не удалось сохранить коллекцию");
        }
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
        return new ExitCommand(server, printer);
    }
}
