package com.alame.lab6.client.input.readers.elements.file;

import com.alame.lab6.client.input.readers.FileReader;
import com.alame.lab6.client.input.readers.elements.StudyGroupReader;
import com.alame.lab6.common.data.*;
import com.alame.lab6.common.exceptions.IncorrectElementFieldException;
import com.alame.lab6.common.parsers.StudyGroupParser;
import java.io.IOException;

/**
 * class for reading studyGroup from file
 */
public class FileStudyGroupReader implements StudyGroupReader {
    /**
     * field that realise reading person
     */
    private final FilePersonReader filePersonReader;
    /**
     * field that realise reading from file
     */
    private final FileReader fileReader;
    private final StudyGroupParser studyGroupParser;
    /**
     * field that realise reading coordinates
     */
    private final FileCoordinatesReader fileCoordinatesReader;
    public FileStudyGroupReader(FileReader fileReader, FilePersonReader filePersonReader,
                                StudyGroupParser studyGroupParser, FileCoordinatesReader fileCoordinatesReader) {
        this.fileReader = fileReader;
        this.filePersonReader = filePersonReader;
        this.studyGroupParser = studyGroupParser;
        this.fileCoordinatesReader = fileCoordinatesReader;
    }

    /**
     * read studyGroup from file
     * @return received studyGroup
     * @throws IncorrectElementFieldException if studyGroup is not valid
     */
    @Override
    public StudyGroup readStudyGroup() throws IncorrectElementFieldException {
        return new StudyGroup(readName(), readCoordinates(), readStudentsCount(),
                readExpelledStudent(), readFormOfEducation(), readSemester(), readPerson());
    }
    /**
     * read name from file
     * @return received name
     * @throws IncorrectElementFieldException if name is not valid
     */
    @Override
    public String readName() throws IncorrectElementFieldException {
        try{
            String nextLine = fileReader.getNextLine();
            if(nextLine.equals("")) nextLine = null;
            return studyGroupParser.parseName(nextLine);
        } catch (IOException e) {
            throw new IncorrectElementFieldException(e.getMessage());
        }
    }
    /**
     * read coordinates from file
     * @return received coordinates
     * @throws IncorrectElementFieldException if coordinates is not valid
     */
    @Override
    public Coordinates readCoordinates() throws IncorrectElementFieldException {
        return fileCoordinatesReader.readCoordinates();
    }
    /**
     * read studentsCount from file
     * @return received studentsCount
     * @throws IncorrectElementFieldException if studentsCount is not valid
     */
    @Override
    public int readStudentsCount() throws IncorrectElementFieldException {
        try{
            String nextLine = fileReader.getNextLine();
            if(nextLine.equals("")) nextLine = null;
            return studyGroupParser.parseStudentsCount(nextLine);
        } catch (IOException e) {
            throw new IncorrectElementFieldException(e.getMessage());
        }
    }
    /**
     * read expelledStudents from file
     * @return received expelledStudents
     * @throws IncorrectElementFieldException if expelledStudents is not valid
     */
    @Override
    public long readExpelledStudent() throws IncorrectElementFieldException {
        try {
            String nextLine = fileReader.getNextLine();
            if(nextLine.equals("")) nextLine = null;
            return studyGroupParser.parseExpelledStudents(nextLine);
        } catch (IOException e) {
            throw new IncorrectElementFieldException(e.getMessage());
        }
    }
    /**
     * read formOfEducation from file
     * @return received formOfEducation
     * @throws IncorrectElementFieldException if formOfEducation is not valid
     */
    @Override
    public FormOfEducation readFormOfEducation() throws IncorrectElementFieldException {
        try {
            String nextLine = fileReader.getNextLine();
            if(nextLine.equals("")) nextLine = null;
            return studyGroupParser.parseFormOfEducation(nextLine);
        } catch (IOException e) {
            throw new IncorrectElementFieldException(e.getMessage());
        }
    }
    /**
     * read semester from file
     * @return received semester
     * @throws IncorrectElementFieldException if semester is not valid
     */
    @Override
    public Semester readSemester() throws IncorrectElementFieldException {
        try {
            String nextLine = fileReader.getNextLine();
            if(nextLine.equals("")) nextLine = null;
            return studyGroupParser.parseSemester(nextLine);
        } catch (IOException e) {
            throw new IncorrectElementFieldException(e.getMessage());
        }
    }
    /**
     * read person from file
     * @return received person
     * @throws IncorrectElementFieldException if person is not valid
     */
    @Override
    public Person readPerson() throws IncorrectElementFieldException {
        return filePersonReader.readPerson();
    }


}
