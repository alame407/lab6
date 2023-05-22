package com.alame.lab6.common.printers;

import com.alame.lab6.common.data.StudyGroup;

/**
 * interface for all classes that print something on user screen
 */
public interface Printer {
    void printObject(Object object);

    void printlnObject(Object object);
    void printString(String string);
    void printlnString(String string);
    void printStudyGroup(StudyGroup studyGroup);
    void printlnStudyGroup(StudyGroup studyGroup);
}
