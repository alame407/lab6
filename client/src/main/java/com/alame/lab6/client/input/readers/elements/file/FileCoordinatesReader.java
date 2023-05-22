package com.alame.lab6.client.input.readers.elements.file;

import com.alame.lab6.client.input.readers.FileReader;
import com.alame.lab6.common.data.Coordinates;
import com.alame.lab6.common.exceptions.IncorrectElementFieldException;
import com.alame.lab6.common.parsers.CoordinatesParser;

import java.io.IOException;

/**
 * class for reading coordinates from file
 */
public class FileCoordinatesReader {
    /**
     * field that realise reading from file
     */
    private final FileReader fileReader;
    private final CoordinatesParser coordinatesParser;
    public FileCoordinatesReader(FileReader fileReader, CoordinatesParser coordinatesParser) {
        this.fileReader = fileReader;
        this.coordinatesParser = coordinatesParser;
    }

    /**
     * read coordinates from file
     * @return received coordinates
     * @throws IncorrectElementFieldException if coordinates is not valid
     */
    public Coordinates readCoordinates() throws IncorrectElementFieldException {
        return new Coordinates(readX(), readY());
    }

    /**
     * read x from file
     * @return received x
     * @throws IncorrectElementFieldException if x is not valid
     */
    public Long readX() throws IncorrectElementFieldException{
        try{
            String nextLine = fileReader.getNextLine();
            if(nextLine.equals("")) nextLine = null;
            return coordinatesParser.parseX(nextLine);
        } catch (IOException e) {
            throw new IncorrectElementFieldException(e.getMessage());
        }

    }

    /**
     * read y from file
     * @return received y
     * @throws IncorrectElementFieldException if y is not valid
     */
    public float readY() throws IncorrectElementFieldException{
        try {
            String nextLine = fileReader.getNextLine();
            if(nextLine.equals("")) nextLine = null;
            return coordinatesParser.parseY(nextLine);
        } catch (IOException e) {
            throw new IncorrectElementFieldException(e.getMessage());
        }
    }
}
