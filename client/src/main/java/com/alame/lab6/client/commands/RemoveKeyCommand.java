package com.alame.lab6.client.commands;

import com.alame.lab6.client.utility.network.RequestSender;
import com.alame.lab6.common.commands.Command;
import com.alame.lab6.common.exceptions.IncorrectCommandParameterException;
import com.alame.lab6.common.printers.Printer;
import com.alame.lab6.common.request.KeyExistRequest;
import com.alame.lab6.common.request.RemoveKeyRequest;
import com.alame.lab6.common.response.Response;
import com.alame.lab6.common.response.ResponseStatus;

import java.io.IOException;

/**
 * command for removing key from collection
 */
public class RemoveKeyCommand implements Command {
    private String key;
    private final RequestSender requestSender;
    private final Printer printer;

    public RemoveKeyCommand(RequestSender requestSender, Printer printer) {
        this.requestSender = requestSender;
        this.printer = printer;
    }

    /**
     * remove key from collection
     * @return success of execution
     */
    @Override
    public boolean execute() {
        try {
            Response<String> response = requestSender.sendThenReceive(new RemoveKeyRequest(key));
            if (response.getStatus()== ResponseStatus.SUCCESS){
                printer.printlnString(response.getResponse());
                return true;
            }
            else {
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
     * @throws IncorrectCommandParameterException if parameters size!=1 or key doesn't exist
     */
    @Override
    public void setParameters(String[] parameters) throws IncorrectCommandParameterException {
        if (parameters.length!=1) throw new IncorrectCommandParameterException("Данная команда принимает 1 аргумент");
        try{
            key = parameters[0];
            Response response = requestSender.sendThenReceive(new KeyExistRequest(key));
            if (response.getResponse().equals("false")){
                throw new IncorrectCommandParameterException("такого ключа не существует");
            }
        }
        catch (IOException e){
            throw new IncorrectCommandParameterException(e.getMessage());
        }

    }

    /**
     * @return command description
     */
    @Override
    public String description() {
        return "remove_key key: Удаляет элемент коллекции с заданным ключом";
    }

    /**
     * @return command name
     */
    @Override
    public String name() {
        return "remove_key";
    }

    /**
     * create new RemoveKeyCommand
     * @return new RemoveKeyCommand with same receiver
     */
    @Override
    public Command newInstance() {
        return new RemoveKeyCommand(requestSender, printer);
    }
}
