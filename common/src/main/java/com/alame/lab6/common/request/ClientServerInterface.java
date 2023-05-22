package com.alame.lab6.common.request;

import com.alame.lab6.common.data.*;
import com.alame.lab6.common.exceptions.CollectionFullException;
import com.alame.lab6.common.exceptions.CollectionIsEmptyException;
import com.alame.lab6.common.exceptions.IncorrectElementFieldException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * interface server for a client project
 */
public interface ClientServerInterface {
    /**
     * info about collection
     * @return info
     */
    String info();

    /**
     * get all values of collection
     * @return all values
     */
    List<StudyGroup> getAllValues();

    /**
     * insert pair key, element in collection
     * @param key - key to insert
     * @param element - element to insert
     * @throws IncorrectElementFieldException if element is not valid
     */
    void insert(String key, StudyGroup element) throws IncorrectElementFieldException;
    /**
     * update fields of element
     * @param id - id of element
     * @param name - name of element
     * @param coordinates - coordinates of element
     * @param studentsCount - studentsCount of element
     * @param expelledStudents - expelledStudents of element
     * @param formOfEducation - formOfEducation of element
     * @param semester - semester of element
     * @param groupAdmin - groupAdmin of element
     * @throws IncorrectElementFieldException if fields are not valid
     */

    void update(int id, String name, Coordinates coordinates, int studentsCount, long expelledStudents,
                FormOfEducation formOfEducation, Semester semester, Person groupAdmin)
            throws IncorrectElementFieldException;
    /**
     * delete value by key
     * @param key - key in map
     */
    void removeKey(String key);
    /**
     * clear collection
     */
    void clear();
    /**
     * delete all keys which less than iven
     * @param key - key for compare
     */
    void removeLowerKey(String key);
    /**
     * replace element by key if element less than given
     * @param key - key in map
     * @param newStudyGroup - new element value
     */
    void replaceIfLower(String key, StudyGroup newStudyGroup) throws IncorrectElementFieldException;
    /**
     * delete all elements where formOfEducation equals to given
     * @param formOfEducation - formOfEducation to delete
     */
    void removeAllByFormOfEducation(FormOfEducation formOfEducation)throws IncorrectElementFieldException;
    /**
     * find max element by creationDate
     * @return max element by creation date
     * @throws CollectionIsEmptyException - if collection size=0
     */
    StudyGroup getMaxByCreationDate() throws CollectionIsEmptyException;
    /**
     * get all group admins of study groups in reverse order
     * @return list of group admins
     */
    List<Person> getAllGroupAdminsInReverseOrder();
    /**
     * put all values in map in collection
     * @param studyGroupMap - values to put
     */
    void putAll(Map<String, StudyGroup> studyGroupMap)throws IncorrectElementFieldException;
    /**
     * check if id represented in collection
     * @param id - id of element
     * @return true if id represented in collection, else false
     */
    boolean idExist(int id);
    /**
     * check if key represented in collection
     * @param key - key in map
     * @return true if key represented in collection, else false
     */
    boolean keyExist(String key);
    /**
     * generate next unique id
     * @return next id
     * @throws CollectionFullException all id already represented in collection
     */
    int getNextId() throws CollectionFullException;
    /**
     * generate creation date
     * @return current date
     */
    LocalDate getCreationDate();
}
