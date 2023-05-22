package com.alame.lab6.client.input.readers;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * class for reading from console
 */
public abstract class ConsoleReader {
    /**
     * field that realise reading
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * get next line from console
     * @return next line
     */
    protected String getNextLine(){
        String string = "";
        try{
            string = scanner.nextLine();
        }
        catch (NoSuchElementException  e){
            System.exit(0);
        }
        return string;
    }
}
