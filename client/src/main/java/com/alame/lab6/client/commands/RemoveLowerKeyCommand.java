package com.alame.lab6.client.commands;


import com.alame.lab6.client.utility.network.RequestSender;
import com.alame.lab6.common.commands.Command;
import com.alame.lab6.common.exceptions.IncorrectCommandParameterException;
import com.alame.lab6.common.printers.Printer;
import com.alame.lab6.common.request.RemoveLowerKeyRequest;
import com.alame.lab6.common.response.Response;
import com.alame.lab6.common.response.ResponseStatus;

import java.io.IOException;

/**
 * command for removing keys from collection that less than given
 */
public class RemoveLowerKeyCommand implements Command {
    private String key;
    private final RequestSender requestSender;
    private final Printer printer;
    public RemoveLowerKeyCommand(RequestSender requestSender, Printer printer){

        this.requestSender = requestSender;
        this.printer = printer;
    }

    /**
     * remove all keys in collection that less than given
     * @return success of execution
     */
    @Override
    public boolean execute() {
        try {
            Response<String> response = requestSender.sendThenReceive(new RemoveLowerKeyRequest(key));
            if (response.getStatus()== ResponseStatus.SUCCESS){
                printer.printlnString(response.getResponse());
                return true;
            }
            else{
                printer.printlnString(response.getErrors());
                return false;
            }
        }
        catch (IOException e){
            printer.printlnString(e.getMessage());
            return false;
        }
    }

    /**
     * set key
     * @param parameters - all parameters of command
     * @throws IncorrectCommandParameterException if parameters size!=1
     */
    @Override
    public void setParameters(String[] parameters) throws IncorrectCommandParameterException {
        if (parameters.length!=1) throw new IncorrectCommandParameterException("Данная команда принимает 1 аргумент");
        this.key = parameters[0];
    }

    /**
     * @return command description
     */
    @Override
    public String description() {
        return "remove_lower_key key: удаляет из коллекции все элементы, ключ которых меньше, чем заданный";
    }

    /**
     * @return command name
     */
    @Override
    public String name() {
        return "remove_lower_key";
    }

    /**
     * create new RemoveLowerKeyCommand
     * @return new RemoveLowerKeyCommand with same receiver
     */
    @Override
    public Command newInstance() {
        return new RemoveLowerKeyCommand(requestSender, printer);
    }
}
