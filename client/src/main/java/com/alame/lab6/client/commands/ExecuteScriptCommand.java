package com.alame.lab6.client.commands;


import com.alame.lab6.client.input.UserInput;
import com.alame.lab6.client.input.readers.FileReader;
import com.alame.lab6.common.exceptions.CommandNotFoundException;
import com.alame.lab6.client.input.readers.commands.CommandReader;
import com.alame.lab6.client.input.readers.commands.file.FileCommandReader;
import com.alame.lab6.client.input.readers.elements.StudyGroupReader;
import com.alame.lab6.client.input.readers.elements.file.FileCoordinatesReader;
import com.alame.lab6.client.input.readers.elements.file.FilePersonReader;
import com.alame.lab6.client.input.readers.elements.file.FileStudyGroupReader;
import com.alame.lab6.common.commands.Command;
import com.alame.lab6.common.exceptions.IncorrectCommandParameterException;
import com.alame.lab6.common.parsers.CommandParser;
import com.alame.lab6.common.parsers.CoordinatesParser;
import com.alame.lab6.common.parsers.PersonParser;
import com.alame.lab6.common.parsers.StudyGroupParser;
import com.alame.lab6.common.printers.Printer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * command for execution commands in script
 */
public class ExecuteScriptCommand implements Command {
    /**
     * files open in all scripts
     */
    private static final Set<String> openFiles = new HashSet<>();
    private FileReader fileReader;
    private StudyGroupReader studyGroupReader;
    private CommandReader commandReader;
    private final Printer printer;
    private final UserInput userInput;
    private final CommandHandler commandHandler;
    private final CommandParser commandParser;
    private final CoordinatesParser coordinatesParser;
    private final PersonParser personParser;
    private final StudyGroupParser studyGroupParser;
    public ExecuteScriptCommand(UserInput userInput, CommandHandler commandHandler,
                                Printer printer, CommandParser commandParser, CoordinatesParser coordinatesParser,
                                PersonParser personParser, StudyGroupParser studyGroupParser){
        this.userInput = userInput;
        this.commandHandler = commandHandler;
        this.printer = printer;
        this.commandParser = commandParser;
        this.coordinatesParser = coordinatesParser;
        this.personParser = personParser;
        this.studyGroupParser = studyGroupParser;
    }

    /**
     * read script and execute commands in script
     * @return true
     */
    @Override
    public boolean execute() {
        openFiles.add(fileReader.getFileName());
        StudyGroupReader oldStudyGroupReader = userInput.getStudyGroupReader() ;
        CommandReader oldCommandReader = userInput.getCommandReader();
        userInput.setCommandReader(commandReader);
        userInput.setStudyGroupReader(studyGroupReader);
        while (fileReader.hasNextLine()){
            try{
                Command command = userInput.getCommandReader().readCommand();
                commandHandler.handle(command);
            }
            catch (IncorrectCommandParameterException | CommandNotFoundException e){
                printer.printlnString("В файле " + fileReader.getFileName() + " ошибка: " + e.getMessage());
            }
        }

        try{
            fileReader.close();
        }
        catch (IOException e){
            printer.printlnString("Произошла ошибка при закрытии файла");
        }
        userInput.setCommandReader(oldCommandReader);
        userInput.setStudyGroupReader(oldStudyGroupReader);
        openFiles.remove(fileReader.getFileName());
        return true;
    }

    /**
     * set filename
     * @param parameters - all parameters of command
     * @throws IncorrectCommandParameterException if file already open or can't be open
     */
    @Override
    public void setParameters(String[] parameters) throws IncorrectCommandParameterException {

        if (parameters.length!=1)
            throw new IncorrectCommandParameterException("Данная команда принимает 1 аргумент - название файла");
        if (openFiles.contains(parameters[0])){
            throw new IncorrectCommandParameterException("Такой файл уже уже открыт, " +
                    "команда execute script не выполняется для избежания рекурсии");
        }
        try{
            fileReader = new FileReader(parameters[0]);
            studyGroupReader = new FileStudyGroupReader(fileReader, new FilePersonReader(fileReader, personParser),
                    studyGroupParser, new FileCoordinatesReader(fileReader, coordinatesParser));
            commandReader = new FileCommandReader(fileReader, commandParser);
        } catch (IOException e) {
            throw new IncorrectCommandParameterException("Не удалось открыть указанный файл");
        }
    }

    /**
     * @return command description
     */
    @Override
    public String description() {
        return "execute_script file_name: считывает и исполняет скрипт из указанного файла.";
    }

    /**
     * @return command name
     */
    @Override
    public String name() {
        return "execute_script";
    }

    /**
     * create new Instance of ExecuteScriptCommand
     * @return new ExecuteScriptCommand with same userInput and commandHandler
     */
    @Override
    public Command newInstance() {
        return new ExecuteScriptCommand(userInput, commandHandler, printer, commandParser,
                coordinatesParser, personParser, studyGroupParser);
    }
}
