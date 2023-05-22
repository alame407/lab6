package com.alame.lab6.common.printers;

import com.alame.lab6.common.data.StudyGroup;

/**
 * class for printing in console
 */
public class ConsolePrinter implements Printer{
    @Override
    public void printObject(Object object) {
        System.out.println(object);
    }

    @Override
    public void printlnObject(Object object) {
        System.out.println(object);
    }

    @Override
    public void printString(String string) {
        System.out.println(string);
    }

    @Override
    public void printlnString(String string) {
        System.out.println(string);
    }

    @Override
    public void printStudyGroup(StudyGroup studyGroup) {
        System.out.println(studyGroup);
    }

    @Override
    public void printlnStudyGroup(StudyGroup studyGroup) {
        System.out.println(studyGroup);
    }
}
