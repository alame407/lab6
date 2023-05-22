package com.alame.lab6.client.commands;

import com.alame.lab6.client.input.UserInput;
import com.alame.lab6.client.input.readers.elements.StudyGroupReader;
import com.alame.lab6.client.utility.network.RequestSender;
import com.alame.lab6.common.commands.Command;
import com.alame.lab6.common.exceptions.IncorrectCommandParameterException;
import com.alame.lab6.common.exceptions.IncorrectElementFieldException;
import com.alame.lab6.common.printers.Printer;
import com.alame.lab6.common.request.IdExistRequest;
import com.alame.lab6.common.request.UpdateRequest;
import com.alame.lab6.common.response.Response;
import com.alame.lab6.common.response.ResponseStatus;

import java.io.IOException;

/**
 * command for updating value by id
 */
public class UpdateCommand implements Command {
    private int id;
    private final UserInput userInput;
    private final Printer printer;
    private final RequestSender requestSender;
    public UpdateCommand(UserInput userInput, Printer printer, RequestSender requestSender){
        this.userInput = userInput;
        this.printer = printer;
        this.requestSender = requestSender;
    }

    /**
     * update fields studyGroup by id
     * @return success of execution
     */
    @Override
    public boolean execute() {
       try{
            StudyGroupReader studyGroupReader = userInput.getStudyGroupReader();
            Response<String> response = requestSender.sendThenReceive(new UpdateRequest(id, studyGroupReader.readName(),
                    studyGroupReader.readCoordinates(), studyGroupReader.readStudentsCount(),
                    studyGroupReader.readExpelledStudent(),
                    studyGroupReader.readFormOfEducation(), studyGroupReader.readSemester(),
                    studyGroupReader.readPerson()));
            if (response.getStatus()== ResponseStatus.SUCCESS){
                printer.printlnString(response.getResponse());
                return true;
            }
            else {
                printer.printlnString(response.getErrors());
                return false;
            }
        }
        catch(IOException | IncorrectElementFieldException e){
            printer.printlnString(e.getMessage());
            return false;
        }
    }

    /**
     * set id
     * @param parameters - all parameters of command
     * @throws IncorrectCommandParameterException if parameters size!=1 or id isn't int or id doesn't exist
     */
    @Override
    public void setParameters(String[] parameters) throws IncorrectCommandParameterException {
        if (parameters.length!=1) throw new IncorrectCommandParameterException("Данная команда принимает 1 аргумент");
        else{
            try{
                id = Integer.parseInt(parameters[0]);
                Response<Boolean> response = requestSender.sendThenReceive(new IdExistRequest(id));
                if (!response.getResponse()){
                    throw new IncorrectCommandParameterException("такого id не существует");
                }
            }
            catch(NumberFormatException e){
                throw new IncorrectCommandParameterException("Аргумент команды должен быть целым числом");
            }
            catch (IOException e){
                throw new IncorrectCommandParameterException(e.getMessage());
            }
        }
    }

    /**
     * @return command description
     */
    @Override
    public String description() {
        return "update id: обновляет значение элемента коллекции, id которого равен заданному";
    }

    /**
     * @return command name
     */
    @Override
    public String name() {
        return "update";
    }

    /**
     * create new UpdateCommand
     * @return new UpdateCommand with same receiver and userInput
     */
    @Override
    public Command newInstance() {
        return new UpdateCommand(userInput, printer, requestSender);
    }
}
