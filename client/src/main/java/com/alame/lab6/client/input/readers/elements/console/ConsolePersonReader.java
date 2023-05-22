package com.alame.lab6.client.input.readers.elements.console;

import com.alame.lab6.client.input.readers.ConsoleReader;
import com.alame.lab6.common.data.Country;
import com.alame.lab6.common.data.EyesColor;
import com.alame.lab6.common.data.HairColor;
import com.alame.lab6.common.data.Person;
import com.alame.lab6.common.exceptions.IncorrectElementFieldException;
import com.alame.lab6.common.parsers.PersonParser;
import com.alame.lab6.common.printers.Printer;

import java.time.LocalDate;

/**
 * class for reading person from console
 */
public class ConsolePersonReader extends ConsoleReader {
    private final Printer printer;
    private final PersonParser personParser;
    public ConsolePersonReader(Printer printer, PersonParser personParser){
        this.printer = printer;
        this.personParser = personParser;
    }
    /**
     * read person from console
     * @return received person
     */
    public Person readPerson(){
        return new Person(readName(), readBirthday(), readEyesColor(), readHairColor(), readNationality());
    }

    /**
     * read name from console
     * @return received name
     */
    public String readName(){
        printer.printString("Введите имя админа ");
        while (true){
            String nextLine = getNextLine();
            if (nextLine.equals("")) nextLine = null;
            try{
                return personParser.parseName(nextLine);
            }
            catch(IncorrectElementFieldException e){
                printer.printString(e.getMessage() + ", повторите ввод ");
            }
        }
    }

    /**
     * read birthday from console
     * @return received birthday
     */
    public LocalDate readBirthday(){
        printer.printString("Введите день рождения админа ");
        while(true){
            String nextLine = getNextLine();
            if (nextLine.equals("")) nextLine = null;
            try {
                return personParser.parseBirthday(nextLine);
            }
            catch (IncorrectElementFieldException e){
                printer.printString(e.getMessage() + ", повторите ввод ");
            }
        }
    }

    /**
     * read eyesColor from console
     * @return received eyesColor
     */
    public EyesColor readEyesColor(){
        printer.printString("Введите цвет глаз админа возможные варианты " + EyesColor.possibleValues() + " ");
        while(true){
            String nextLine = getNextLine();
            if (nextLine.equals("")) nextLine = null;
            try {
                return personParser.parseEyesColor(nextLine);
            }
            catch (IncorrectElementFieldException e){
                printer.printString(e.getMessage() + ", повторите ввод ");
            }
        }
    }

    /**
     * read hairColor from console
     * @return received hairColor
     */
    public HairColor readHairColor(){
        printer.printString("Введите цвет волос админа возможные варианты " + HairColor.possibleValues() + " ");
        while(true){
            String nextLine = getNextLine();
            if (nextLine.equals("")) nextLine = null;
            try {
                return personParser.parseHairColor(nextLine);
            }
            catch (IncorrectElementFieldException e){
                printer.printString(e.getMessage() + ", повторите ввод ");
            }
        }
    }

    /**
     * read nationality from console
     * @return received nationality
     */
    public Country readNationality(){
        printer.printString("Введите национальность админа возможные варианты " + Country.possibleValues() + " ");
        while(true){
            String nextLine = getNextLine();
            if (nextLine.equals("")) nextLine = null;
            try {
                return personParser.parseCountry(nextLine);
            }
            catch (IncorrectElementFieldException e){
                printer.printString(e.getMessage() + ", повторите ввод ");
            }
        }
    }
}
