package com.alame.lab6.server;

import com.alame.lab6.common.commands.Command;
import com.alame.lab6.common.commands.CommandMapper;
import com.alame.lab6.common.data.CoordinatesValidator;
import com.alame.lab6.common.data.KeyValidator;
import com.alame.lab6.common.data.PersonValidator;
import com.alame.lab6.common.data.StudyGroupValidator;
import com.alame.lab6.common.exceptions.CommandNotFoundException;
import com.alame.lab6.common.exceptions.IncorrectCommandParameterException;
import com.alame.lab6.common.exceptions.IncorrectElementFieldException;
import com.alame.lab6.common.exceptions.IncorrectKeyException;
import com.alame.lab6.common.executors.Executor;
import com.alame.lab6.common.parsers.*;
import com.alame.lab6.common.printers.ConsolePrinter;
import com.alame.lab6.common.printers.Printer;
import com.alame.lab6.server.commands.CommandHandler;
import com.alame.lab6.server.commands.ExitCommand;
import com.alame.lab6.server.commands.SaveCommand;
import com.alame.lab6.server.csv.CsvElementsLoader;
import com.alame.lab6.server.network.FrameMapper;
import com.alame.lab6.server.network.RequestHandler;
import com.alame.lab6.server.input.readers.commads.CommandReader;
import com.alame.lab6.server.input.readers.commads.console.ConsoleCommandReader;
import com.alame.lab6.server.servers.Server;
import com.alame.lab6.server.servers.ServerInterface;
import java.io.*;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class App {
    RequestHandler requestHandler;
    Printer printer;
    public static final Logger logger = Logger.getLogger("ServerLogger");
    ServerInterface server;
    public App(String[] args, Printer printer, ServerInterface server, RequestHandler requestHandler) {
        this.printer = printer;
        this.requestHandler = requestHandler;
        this.server =server;
        if (args.length != 1) {
            this.printer.printlnString("В переданных аргументах не 1 параметр с название файла, " +
                    "в коллекцию ничего не загружено");
        }
        try (Reader reader = new FileReader(args[0])) {
            PersonValidator personValidator = new PersonValidator();
            CoordinatesValidator coordinatesValidator = new CoordinatesValidator();
            StudyGroupValidator studyGroupValidator = new StudyGroupValidator(personValidator, coordinatesValidator);
            new CsvElementsLoader(reader, this.server, new StudyGroupParser(studyGroupValidator),
                    new PersonParser(personValidator), new CoordinatesParser(coordinatesValidator),
                    new KeyParser(new KeyValidator()), studyGroupValidator).load();
        } catch (IOException e) {
            this.printer.printlnString("Не удалось прочитать файл, в коллекцию ничего не загружено");
        } catch (IncorrectElementFieldException | IncorrectKeyException e) {
            this.printer.printlnString("В файле присутствуют невалидные элементы: " + e.getMessage() +
                    ", в коллекцию ничего не загружено");
        } catch (IllegalArgumentException e) {
            this.printer.printlnString("файл должен должен быть в формате: key, id, name, Coordinates x, Coordinates y, creationDate,"
                    + "studentsCount, expelledStudents, formOfEducation, semesterEnum, groupAdmin name," +
                    "groupAdmin birthday, groupAdmin eyeColor, groupAdmin hairColor, groupAdmin nationality" +
                    " разделенные запятыми, в коллекцию ничего не загружено");
        }
        try {
            FileHandler fileHandler = new FileHandler("status.log");
            logger.addHandler(fileHandler);
        }
        catch (IOException e){
            printer.printlnString(e.getMessage() + " не удалось добавить запись логов в файл");
        }
    }
    public void start(){
        logger.info("сервер запущен");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        CommandMapper commandMapper = new CommandMapper();

        CommandHandler commandHandler = new CommandHandler(new Executor());
        commandMapper.addAllCommands(new HashMap<>(){
            {
                put("save", new SaveCommand(server, printer));
                put("exit", new ExitCommand(server, printer));
            }
        });
        CommandReader commandReader = new ConsoleCommandReader(new CommandParser(commandMapper));
        while(true){
            try {
                requestHandler.ReceiveThenSend();
            }
            catch (IOException e){
                printer.printlnString(e.getMessage());
            }
            try{
                if (bufferedReader.ready()){
                    Command command = commandReader.readCommand();
                    commandHandler.handle(command);
                }
            }catch (IOException | IncorrectCommandParameterException | CommandNotFoundException e){
                printer.printlnString(e.getMessage());
            }


        }
    }
    public static void main(String[] args) {
        Printer printer = new ConsolePrinter();
        if (args.length!=1){
            logger.severe("В переданных аргументах не 1 параметр с название файла, " +
                    "сервер не может быть запущен");
            System.exit(0);
        }
        ServerInterface server = new Server(args[0],
                new StudyGroupValidator(new PersonValidator(), new CoordinatesValidator()));
        try (DatagramChannel datagramChannel = DatagramChannel.open()){
            new App(args, printer, server, new RequestHandler(datagramChannel,server, new FrameMapper(new HashMap<>())))
                    .start();
        }catch (IOException e){

            logger.severe("Не удалось запустить сервер " + e.getMessage());
            System.exit(0);
        }
    }
}