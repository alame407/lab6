package com.alame.lab6.client.input.readers;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * class for reading file
 */
public class FileReader {
    /**
     * field that realise reading
     */
    private final BufferedInputStream bufferedInputStream;
    /**
     * path to file
     */
    private final String fileName;
    /**
     * field that show if file has next line
     */
    private boolean hasNextLine = true;
    public FileReader(String path) throws IOException {
        bufferedInputStream = new BufferedInputStream(new FileInputStream(path));
        fileName = path;
    }

    /**
     * close stream
     * @throws IOException if something goes wrong with file
     */
    public void close() throws IOException{
        bufferedInputStream.close();
    }

    /**
     * @return fileName
     */
    public String getFileName(){
        return fileName;
    }

    /**
     * @return if file has next line
     */
    public boolean hasNextLine(){
        return hasNextLine;
    }

    /**
     * get next line from file, set hasNextLine to false if file end
     * @return next line
     * @throws IOException if something goes wrong with file
     */
    public String getNextLine() throws IOException {
        if (!hasNextLine) throw new IOException("File is end");
        StringBuilder result = new StringBuilder();
        int symbol = bufferedInputStream.read();
        while (symbol != -1){
            result.append((char) symbol);
            if (result.toString().endsWith(System.lineSeparator())){
                return result.substring(0, result.length()-System.lineSeparator().length());
            }
            symbol = bufferedInputStream.read();
        }
        hasNextLine = false;
        return result.toString();
    }
}
