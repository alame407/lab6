package com.alame.lab6.client.input;

import com.alame.lab6.client.input.readers.commands.CommandReader;
import com.alame.lab6.client.input.readers.elements.StudyGroupReader;

/**
 * class for getting user input
 */
public class UserInput {
    /**
     * current StudyGroupReader
     */
    private StudyGroupReader studyGroupReader;
    /**
     * current CommandReader
     */
    private CommandReader commandReader;
    public UserInput(CommandReader commandReader, StudyGroupReader studyGroupReader){
        this.commandReader = commandReader;
        this.studyGroupReader = studyGroupReader;
    }

    /**
     * set current CommandReader
     * @param commandReader - current CommandReader
     */
    public void setCommandReader(CommandReader commandReader) {
        this.commandReader = commandReader;
    }

    /**
     * set current StudyGroupReader
     * @param studyGroupReader - current StudyGroupReader
     */
    public void setStudyGroupReader(StudyGroupReader studyGroupReader) {
        this.studyGroupReader = studyGroupReader;
    }

    /**
     * @return current CommandReader
     */
    public CommandReader getCommandReader() {
        return commandReader;
    }

    /**
     * @return current StudyGroupReader
     */
    public StudyGroupReader getStudyGroupReader() {
        return studyGroupReader;
    }

}
