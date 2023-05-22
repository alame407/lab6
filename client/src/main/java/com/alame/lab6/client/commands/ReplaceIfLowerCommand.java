package com.alame.lab6.client.commands;

import com.alame.lab6.client.input.UserInput;
import com.alame.lab6.client.utility.network.RequestSender;
import com.alame.lab6.common.commands.Command;
import com.alame.lab6.common.exceptions.IncorrectCommandParameterException;
import com.alame.lab6.common.exceptions.IncorrectElementFieldException;
import com.alame.lab6.common.printers.Printer;
import com.alame.lab6.common.request.KeyExistRequest;
import com.alame.lab6.common.request.ReplaceIfLowerRequest;
import com.alame.lab6.common.response.Response;
import com.alame.lab6.common.response.ResponseStatus;

import java.io.IOException;

/**
 * class for replacing element by key if element greater than given
 */
public class ReplaceIfLowerCommand implements Command {
    private  String key;
    private final UserInput userInput;
    private final RequestSender requestSender;
    private final Printer printer;
    public ReplaceIfLowerCommand(UserInput userInput, RequestSender requestSender, Printer printer){
        this.userInput = userInput;
        this.requestSender = requestSender;
        this.printer = printer;
    }

    /**
     * replace value in collection by key if new value less than old
     * @return success of execution
     */
    @Override
    public boolean execute() {
        try{
            Response<String> response = requestSender.sendThenReceive(
                    new ReplaceIfLowerRequest(userInput.getStudyGroupReader().readStudyGroup(), key));
            if (response.getStatus()== ResponseStatus.SUCCESS){
                printer.printlnString(response.getResponse());
                return true;
            }
            else{
                printer.printlnString(response.getErrors());
                return false;
            }
        }
        catch (IOException | IncorrectElementFieldException e){
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
        return "replace_if_lower key {element}: заменяет значение по ключу, если новое значение меньше старого";
    }

    /**
     * @return command name
     */
    @Override
    public String name() {
        return "replace_if_lower";
    }

    /**
     * create new ReplaceIfLowerCommand
     * @return new ReplaceIfLowerCommand with same receiver and userInput
     */
    @Override
    public Command newInstance() {
        return new ReplaceIfLowerCommand(userInput, requestSender, printer);
    }

}
