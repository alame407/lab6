package com.alame.lab6.client.input.readers.elements;

import com.alame.lab6.common.data.*;
import com.alame.lab6.common.exceptions.IncorrectElementFieldException;

/**
 * interface for all class that realise reading studyGroup
 */
public interface StudyGroupReader {
    /**
     * read StudyGroup
     * @return received StudyGroup
     * @throws IncorrectElementFieldException if studyGroup is not valid
     */
    StudyGroup readStudyGroup() throws IncorrectElementFieldException;

    /**
     * read name
     * @return received name
     * @throws IncorrectElementFieldException if name is not valid
     */
    String readName() throws IncorrectElementFieldException;

    /**
     * read coordinates
     * @return received coordinates
     * @throws IncorrectElementFieldException if coordinates are not valid
     */
    Coordinates readCoordinates() throws IncorrectElementFieldException;

    /**
     * read studentsCount
     * @return received studentsCount
     * @throws IncorrectElementFieldException if studentsCount is not valid
     */
    int readStudentsCount() throws IncorrectElementFieldException;

    /**
     * read expelledStudents
     * @return received expelledStudents
     * @throws IncorrectElementFieldException if expelledStudents is not valid
     */

    long readExpelledStudent() throws IncorrectElementFieldException;

    /**
     * read formOfEducation
     * @return received formOfEducation
     * @throws IncorrectElementFieldException if formOfEducation is not valid
     */
    FormOfEducation readFormOfEducation() throws IncorrectElementFieldException;

    /**
     * read semester
     * @return received semester
     * @throws IncorrectElementFieldException if semester is not valid
     */

    Semester readSemester() throws IncorrectElementFieldException;

    /**
     * read person
     * @return received person
     * @throws IncorrectElementFieldException if person is not valid
     */

    Person readPerson() throws IncorrectElementFieldException;

}
