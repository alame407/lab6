package com.alame.lab6.client.input.readers.elements.console;

import com.alame.lab6.client.input.readers.ConsoleReader;
import com.alame.lab6.common.data.Coordinates;
import com.alame.lab6.common.exceptions.IncorrectElementFieldException;
import com.alame.lab6.common.parsers.CoordinatesParser;
import com.alame.lab6.common.printers.Printer;

/**
 * class for reading coordinates from console
 */
public class ConsoleCoordinatesReader extends ConsoleReader {
    private final Printer printer;
    private final CoordinatesParser coordinatesParser;
    public ConsoleCoordinatesReader(Printer printer, CoordinatesParser coordinatesParser){
        this.printer=printer;
        this.coordinatesParser = coordinatesParser;
    }

    /**
     * read coordinates from console
     * @return received coordinate
     */
    public Coordinates readCoordinates(){
        return new Coordinates(readX(), readY());
    }

    /**
     * read x from console
     * @return received x
     */
    public Long readX(){
        printer.printString("Введите координату x ");
        while(true){
            String nextLine = getNextLine();
            if (nextLine.equals("")) nextLine = null;
            try{
                return coordinatesParser.parseX(nextLine);
            }
            catch (IncorrectElementFieldException e){
                printer.printString(e.getMessage() + ", повторите ввод ");
            }
        }
    }

    /**
     * read y from console
     * @return received y
     */
    public float readY(){
        printer.printString("Введите координату y ");
        while(true){
            String nextLine = getNextLine();
            if (nextLine.equals("")) nextLine = null;
            try{
                return coordinatesParser.parseY(nextLine);
            }
            catch (IncorrectElementFieldException e){
                printer.printString(e.getMessage() + ", повторите ввод ");
            }
        }
    }
}
