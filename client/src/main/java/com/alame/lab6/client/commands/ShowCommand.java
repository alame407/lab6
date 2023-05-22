package com.alame.lab6.client.commands;


import com.alame.lab6.client.utility.network.RequestSender;
import com.alame.lab6.common.commands.Command;
import com.alame.lab6.common.data.StudyGroup;
import com.alame.lab6.common.exceptions.IncorrectCommandParameterException;
import com.alame.lab6.common.printers.Printer;
import com.alame.lab6.common.request.GetAllValuesRequest;
import com.alame.lab6.common.response.Response;
import com.alame.lab6.common.response.ResponseStatus;

import java.io.IOException;
import java.util.List;

/**
 * command for showing all elements in collection
 */
public class ShowCommand implements Command {
    private final Printer printer;
    private final RequestSender requestSender;
    public ShowCommand(Printer printer, RequestSender requestSender){
        this.printer = printer;
        this.requestSender = requestSender;
    }

    /**
     * show all elements in collection
     * @return success of execution
     */
    @Override
    public boolean execute() {
        Response<List<StudyGroup>> response;
        try {
            response = requestSender.sendThenReceive(new GetAllValuesRequest());
            if (response.getStatus()== ResponseStatus.SUCCESS){
                for(StudyGroup studyGroup: response.getResponse()){
                    printer.printlnStudyGroup(studyGroup);
                }
                return true;
            }
            else{
                printer.printlnString(response.getErrors());
                return false;
            }
        }
        catch (IOException e){
            printer.printlnString("не удалось получить ответ сервера");
            return false;
        }
    }

    /**
     * set no parameters
     * @param parameters - all parameters of command
     * @throws IncorrectCommandParameterException if parameters size!=0
     */
    @Override
    public void setParameters(String[] parameters)  throws IncorrectCommandParameterException {
        if (parameters.length!=0) throw new IncorrectCommandParameterException("Данная команда не принимает аргументов");
    }

    /**
     * @return command description
     */
    @Override
    public String description() {
        return "show: выводит элементы коллекции в строковом представлении";
    }

    /**
     * @return command name
     */
    @Override
    public String name() {
        return "show";
    }

    /**
     * create new ShowCommand
     * @return new ShowCommand with same receiver
     */
    @Override
    public Command newInstance() {
        return new ShowCommand(printer, requestSender);
    }
}
