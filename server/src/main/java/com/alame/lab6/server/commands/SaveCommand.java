package com.alame.lab6.server.commands;

import com.alame.lab6.common.commands.Command;
import com.alame.lab6.common.exceptions.IncorrectCommandParameterException;
import com.alame.lab6.common.printers.Printer;
import com.alame.lab6.server.servers.ServerInterface;

import java.io.IOException;

/**
 * command for save collection
 */
public class SaveCommand implements Command {
    private final ServerInterface server;
    private final Printer printer;

    public SaveCommand(ServerInterface server, Printer printer) {
        this.server = server;
        this.printer = printer;
    }

    /**
     * save collection
     * @return success of saving
     */
    @Override
    public boolean execute() {
        try {
            server.save();
            return true;
        }
        catch (IOException e){
            printer.printlnString(e.getMessage() + " не удалось сохранить коллекцию");
            return false;
        }
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
        return "save: сохраняет коллекцию в файл";
    }
    /**
     * @return command name
     */
    @Override
    public String name() {
        return "save";
    }

    /**
     * create new SaveCommand
     * @return new SaveCommand
     */
    @Override
    public Command newInstance() {
        return new SaveCommand(server, printer);
    }
}
