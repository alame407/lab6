package com.alame.lab6.client.input.readers.elements.console;

import com.alame.lab6.client.input.readers.ConsoleReader;
import com.alame.lab6.client.input.readers.elements.StudyGroupReader;
import com.alame.lab6.common.data.*;
import com.alame.lab6.common.exceptions.IncorrectElementFieldException;
import com.alame.lab6.common.parsers.StudyGroupParser;
import com.alame.lab6.common.printers.Printer;

/**
 * class for reading StudyGroup from console
 */
public class ConsoleStudyGroupReader extends ConsoleReader implements StudyGroupReader {
    /**
     * field that realise reading coordinates
     */
    private final ConsoleCoordinatesReader consoleCoordinatesReader;
    /**
     * field that realise reading person
     */
    private final ConsolePersonReader consolePersonReader;
    private final Printer printer;
    private final StudyGroupParser studyGroupParser;
    public ConsoleStudyGroupReader(Printer printer, ConsolePersonReader consolePersonReader,
                                   ConsoleCoordinatesReader consoleCoordinatesReader, StudyGroupParser studyGroupParser){
        this.printer = printer;
        this.consoleCoordinatesReader = consoleCoordinatesReader;
        this.consolePersonReader = consolePersonReader;
        this.studyGroupParser = studyGroupParser;
    }

    /**
     * read studyGroup from console
     * @return received studyGroup
     */
    @Override
    public StudyGroup readStudyGroup(){
        return new StudyGroup(readName(), readCoordinates(), readStudentsCount(),
                readExpelledStudent(), readFormOfEducation(), readSemester(), readPerson());
    }

    /**
     * read name from console
     * @return received name
     */
    @Override
    public String readName(){
        printer.printString("Введите имя группы ");
        while(true){
            String nextLine = getNextLine();
            if (nextLine.equals("")) nextLine = null;
            try{
                return studyGroupParser.parseName(nextLine);
            }
            catch (IncorrectElementFieldException e){
                printer.printString(e.getMessage() + ", повторите ввод ");
            }
        }
    }

    /**
     * read coordinates from console
     * @return received coordinates
     */
    @Override
    public Coordinates readCoordinates(){
        return consoleCoordinatesReader.readCoordinates();
    }

    /**
     * read studentsCount from console
     * @return received studentsCount
     */
    @Override
    public int readStudentsCount(){
        printer.printString("Введите количество студентов ");
        while (true){
            String nextLine = getNextLine();
            if (nextLine.equals("")) nextLine = null;
            try{
                return studyGroupParser.parseStudentsCount(nextLine);
            }
            catch (IncorrectElementFieldException e){
                printer.printString(e.getMessage() + ", повторите ввод ");
            }
        }
    }

    /**
     * read expelledStudents from console
     * @return received expelledStudents
     */
    @Override
    public long readExpelledStudent(){
        printer.printString("Введите количество отчисленных студентов ");
        while (true){
            String nextLine = getNextLine();
            if (nextLine.equals("")) nextLine = null;
            try {
                return studyGroupParser.parseExpelledStudents(nextLine);
            }
            catch (IncorrectElementFieldException e){
                printer.printlnString(e.getMessage() + ", повторите ввод ");
            }
        }
    }

    /**
     * read formOfEducation from console
     * @return received formOfEducation
     */
    @Override
    public FormOfEducation readFormOfEducation(){
        printer.printString("введите форму обучения, возможные варианты " + FormOfEducation.possibleValues() + " ");
        while(true){
            String nextLine = getNextLine();
            if (nextLine.equals("")) nextLine = null;
            try {
                return studyGroupParser.parseFormOfEducation(nextLine);
            }
            catch(IncorrectElementFieldException e){
                printer.printString(e.getMessage() + ", повторите ввод ");
            }
        }
    }

    /**
     * read semester from console
     * @return received semester
     */
    @Override
    public Semester readSemester(){
        printer.printString("Введите семестр, возможные варианты " + Semester.possibleValues() + " ");
        while(true){
            String nextLine = getNextLine();
            if (nextLine.equals("")) nextLine = null;
            try {
                return studyGroupParser.parseSemester(nextLine);
            }
            catch(IncorrectElementFieldException e){
                printer.printString(e.getMessage() + ", повторите ввод ");
            }
        }
    }

    /**
     * read person from console
     * @return received person
     */
    @Override
    public Person readPerson(){
        return consolePersonReader.readPerson();
    }
}
