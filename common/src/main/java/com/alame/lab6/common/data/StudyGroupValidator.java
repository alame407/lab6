package com.alame.lab6.common.data;


import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * class for valid studyGroup fields
 */
public class StudyGroupValidator {
    private final PersonValidator personValidator;
    private final CoordinatesValidator coordinatesValidator;

    public StudyGroupValidator(PersonValidator personValidator, CoordinatesValidator coordinatesValidator) {
        this.personValidator = personValidator;
        this.coordinatesValidator = coordinatesValidator;
    }

    /**
     * valid studyGroup
     * @param studyGroup - studyGroup to valid
     * @return result of validation
     */
    public boolean validStudyGroup(StudyGroup studyGroup){
        return validId(studyGroup.getId()) && validName(studyGroup.getName()) &&
                validCoordinates(studyGroup.getCoordinates()) &&  validCreationDate(studyGroup.getCreationDate()) &&
                validStudentsCount(studyGroup.getStudentsCount()) &&
                validExpelledStudents(studyGroup.getExpelledStudents()) &&
                validFormOfEducation(studyGroup.getFormOfEducation()) &&
                validSemester(studyGroup.getSemesterEnum()) && validPerson(studyGroup.getGroupAdmin());
    }

    /**
     * valid name
     * @param name - name to valid
     * @return result of validation
     */
    public boolean validName(String name){
        return !(name == null || name.equals(""));
    }

    /**
     * valid id
     * @param id - id to valid
     * @return result of validation
     */
    public boolean validId(int id){
        return id>0;
    }

    /**
     * valid coordinates
     * @param coordinates - coordinates to valid
     * @return result of validation
     */
    public boolean validCoordinates(Coordinates coordinates){
        return coordinatesValidator.validCoordinates(coordinates);
    }

    /**
     * valid creationDate
     * @param creationDate - creationDate to valid
     * @return result of validation
     */
    public boolean validCreationDate(LocalDate creationDate){
        return !(creationDate == null);
    }

    /**
     * valid studentsCount
     * @param studentsCount - studentsCount to valid
     * @return result of validation
     */
    public boolean validStudentsCount(int studentsCount){
        return studentsCount>0;
    }

    /**
     * valid expelledStudents
     * @param expelledStudents - expelledStudents to valid
     * @return resultOfValidation
     */
    public boolean validExpelledStudents(long expelledStudents){
        return expelledStudents>0;
    }

    /**
     * valid formOfEducation
     * @param formOfEducation - formOfEducation to valid
     * @return result of validation
     */
    public boolean validFormOfEducation(String formOfEducation){
        return formOfEducation==null || FormOfEducation.constantExist(formOfEducation);
    }
    /**
     * valid formOfEducation
     * @param formOfEducation - formOfEducation to valid
     * @return result of validation
     */
    public boolean validFormOfEducation(FormOfEducation formOfEducation){
        return true;
    }

    /**
     * valid semester
     * @param semester - semester to valid
     * @return result of validation
     */
    public boolean validSemester(String semester){
        return semester==null || Semester.constantExist(semester);
    }
    /**
     * valid semester
     * @param semester - semester to valid
     * @return result of validation
     */
    public boolean validSemester(Semester semester){
        return true;
    }

    /**
     * valid person
     * @param person - person to valid
     * @return result of validation
     */
    public boolean validPerson(Person person){
        return personValidator.validPerson(person);
    }

    /**
     * valid ids in collection
     * @param studyGroups - collection of studyGroup yo valid
     * @return result of validation
     */
    public boolean validCollectionId(Collection<StudyGroup> studyGroups){
        Set<Integer> id = new HashSet<>();
        studyGroups.forEach(element -> id.add(element.getId()));
        return id.size() == studyGroups.size();
    }
}
