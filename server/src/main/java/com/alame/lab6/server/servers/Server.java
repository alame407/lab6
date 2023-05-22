package com.alame.lab6.server.servers;

import com.alame.lab6.common.data.*;
import com.alame.lab6.common.exceptions.CollectionFullException;
import com.alame.lab6.common.exceptions.CollectionIsEmptyException;
import com.alame.lab6.common.exceptions.IncorrectElementFieldException;
import com.alame.lab6.server.App;
import com.alame.lab6.server.csv.CsvElementsWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;

/**
 * class for collection control
 */
public class Server implements ServerInterface {
    /**
     * collection
     */
    private final Map<String, StudyGroup> studyGroupMap;
    /**
     * date the collection was created
     */
    private final java.time.LocalDate creationDate;
    private final String fileName;
    /**
     * type of collection
     */
    private final String COLLECTION_TYPE = "TreeMap";
    private final Logger logger = App.logger;
    private final StudyGroupValidator studyGroupValidator;
    public Server(String fileName, StudyGroupValidator studyGroupValidator) {
        this.studyGroupValidator = studyGroupValidator;
        studyGroupMap = new TreeMap<>();
        creationDate = java.time.LocalDate.now();
        this.fileName = fileName;
    }

    /**
     * @return info about collection
     */
    @Override
    public String info(){
        return "тип коллекции: " + COLLECTION_TYPE + " дата создания коллекции: " + creationDate +
                " количество элементов: " + studyGroupMap.size();
    }

    /**
     * @return all studyGroups in collection
     */
    @Override
    public List<StudyGroup> getAllValues(){
        return new ArrayList<>(studyGroupMap.values());
    }

    /**
     * insert a pair of key and element in collection
     * @param key - key in map
     * @param element - value in map
     */
    @Override
    public void insert(String key, StudyGroup element) throws IncorrectElementFieldException{
        if (!(studyGroupValidator.validStudyGroup(element) || idExist(element.getId())))
            throw new IncorrectElementFieldException("Поля коллекции невалидные");
        if (idExist(element.getId()))throw new IncorrectElementFieldException("элемент с таким id уже существует");
        studyGroupMap.put(key, element);
    }

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
    @Override
    public void update(int id, String name, Coordinates coordinates, int studentsCount, long expelledStudents,
                       FormOfEducation formOfEducation, Semester semester, Person groupAdmin)
            throws IncorrectElementFieldException {
        if (!(studyGroupValidator.validName(name) && studyGroupValidator.validCoordinates(coordinates) &&
                studyGroupValidator.validStudentsCount(studentsCount) &&
                studyGroupValidator.validExpelledStudents(expelledStudents) &&
                studyGroupValidator.validFormOfEducation(formOfEducation) && studyGroupValidator.validSemester(semester)&&
                 studyGroupValidator.validPerson(groupAdmin)))
            throw new IncorrectElementFieldException("поля элемента не валидны");
        for(StudyGroup studyGroup: studyGroupMap.values()){
            if (studyGroup.getId()==id) {
                studyGroup.setGroupAdmin(groupAdmin);
                studyGroup.setName(name);
                studyGroup.setCoordinates(coordinates);
                studyGroup.setExpelledStudents(expelledStudents);
                studyGroup.setFormOfEducation(formOfEducation);
                studyGroup.setSemesterEnum(semester);
                studyGroup.setStudentsCount(studentsCount);
                return;
            }

        }
    }

    /**
     * delete value by key
     * @param key - key in map
     */
    @Override
    public void removeKey(String key){
        studyGroupMap.remove(key);
    }

    /**
     * clear collection
     */
    @Override
    public void clear(){
        studyGroupMap.clear();
    }

    /**
     * delete all keys which less than iven
     * @param key - key for compare
     */
    @Override
    public void removeLowerKey(String key){
        studyGroupMap.keySet().removeIf(keyElement -> keyElement.compareTo(key)<0);
    }

    /**
     * replace element by key if element less than given
     * @param key - key in map
     * @param newStudyGroup - new element value
     */
    @Override
    public void replaceIfLower(String key, StudyGroup newStudyGroup) throws IncorrectElementFieldException{
        if (!(studyGroupValidator.validStudyGroup(newStudyGroup)||idExist(newStudyGroup.getId())))
            throw new IncorrectElementFieldException("элемент не валидный");
        if(studyGroupMap.containsKey(key)){
            StudyGroup studyGroup = studyGroupMap.get(key);
            if (newStudyGroup.compareTo(studyGroup) < 0) studyGroupMap.replace(key, newStudyGroup);
        }
    }

    /**
     * delete all elements where formOfEducation equals to given
     * @param formOfEducation - formOfEducation to delete
     */
    @Override
    public void removeAllByFormOfEducation(FormOfEducation formOfEducation)throws IncorrectElementFieldException{
        if (!studyGroupValidator.validFormOfEducation(formOfEducation))
            throw new IncorrectElementFieldException("форма обучения не валидна");
        studyGroupMap.entrySet().removeIf(entry -> entry.getValue().getFormOfEducation()==formOfEducation);
    }

    /**
     * find max element by creationDate
     * @return max element by creation date
     * @throws CollectionIsEmptyException - if collection size=0
     */
    @Override
    public StudyGroup getMaxByCreationDate() throws CollectionIsEmptyException {
        if (studyGroupMap.size()==0) throw new CollectionIsEmptyException("в коллекции нет элементов");
        return  Collections.max(studyGroupMap.values(), Comparator.comparing(StudyGroup::getCreationDate));
    }

    /**
     * get all group admins of study groups in reverse order
     * @return list of group admins
     */
    @Override
    public List<Person> getAllGroupAdminsInReverseOrder(){
        return studyGroupMap.values().stream().map(StudyGroup::getGroupAdmin).sorted(Comparator.reverseOrder()).toList();
    }

    /**
     * save collection in file
     * @throws IOException if something goes wrong with file
     */
    @Override
    public void save() throws IOException{
        CsvElementsWriter csvElementsWriter = new CsvElementsWriter(new FileWriter(fileName));
        csvElementsWriter.write(studyGroupMap);
        csvElementsWriter.close();
        logger.info("коллекция сохранена в файл " + fileName);
    }

    /**
     * put all values in map in collection
     * @param studyGroupMap - values to put
     */
    @Override
    public void putAll(Map<String, StudyGroup> studyGroupMap) throws IncorrectElementFieldException{
        for(StudyGroup studyGroup:studyGroupMap.values()){
            if (!studyGroupValidator.validStudyGroup(studyGroup)||idExist(studyGroup.getId()))
                throw new IncorrectElementFieldException("поля элемента не валидны");
        }
        this.studyGroupMap.putAll(studyGroupMap);
    }

    /**
     * check if id represented in collection
     * @param id - id of element
     * @return true if id represented in collection, else false
     */
    @Override
    public boolean idExist(int id){
        for (StudyGroup studyGroup: studyGroupMap.values()){
            if (studyGroup.getId()==id) return true;
        }
        return false;
    }

    /**
     * check if key represented in collection
     * @param key - key in map
     * @return true if key represented in collection, else false
     */
    @Override
    public boolean keyExist(String key){
        return studyGroupMap.containsKey(key);
    }

    /**
     * generate next unique id
     * @return next id
     * @throws CollectionFullException all id already represented in collection
     */
    @Override
    public int getNextId() throws CollectionFullException {
        if (studyGroupMap.size() == 0) return 1;
        List<StudyGroup> studyGroups = studyGroupMap.values().stream().sorted(Comparator.comparing(StudyGroup::getId))
                .toList();
        int min = 1;
        for (StudyGroup studyGroup : studyGroups) {
            if (studyGroup.getId() != min) return min;
            min++;
        }
        if (studyGroups.get(studyGroups.size()-1).getId()!=Integer.MAX_VALUE) return min;
        throw new CollectionFullException("Коллекция переполнена");
    }

    /**
     * generate creation date
     * @return current date
     */
    @Override
    public LocalDate getCreationDate() {
        return LocalDate.now();
    }
}
