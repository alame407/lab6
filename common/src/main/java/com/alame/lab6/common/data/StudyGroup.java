package com.alame.lab6.common.data;

import com.alame.lab6.common.exceptions.IncorrectElementFieldException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class StudyGroup implements Comparable<StudyGroup>, Serializable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int studentsCount; //Значение поля должно быть больше 0
    private long expelledStudents; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле может быть null
    private Semester semesterEnum; //Поле может быть null
    private Person groupAdmin; //Поле не может быть null
    public StudyGroup(int id, String name, Coordinates coordinates, LocalDate creationDate,int studentsCount,
                      long expelledStudents, FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.expelledStudents = expelledStudents;
        this.studentsCount = studentsCount;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }
    public StudyGroup(String name, Coordinates coordinates, int studentsCount,
                      long expelledStudents, FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin){
        this.name = name;
        this.coordinates = coordinates;
        this.expelledStudents = expelledStudents;
        this.studentsCount = studentsCount;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public int getId() {
        return id;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public long getExpelledStudents() {
        return expelledStudents;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public void setStudentsCount(int studentsCount){
        this.studentsCount = studentsCount;
    }

    public void setSemesterEnum(Semester semesterEnum){
        this.semesterEnum = semesterEnum;
    }

    public void setGroupAdmin(Person groupAdmin){
        this.groupAdmin = groupAdmin;
    }

    public void setFormOfEducation(FormOfEducation formOfEducation){
        this.formOfEducation = formOfEducation;
    }

    public void setExpelledStudents(long expelledStudents){
        this.expelledStudents = expelledStudents;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "StudyGroup{" +
                id +
                ", '" + name + '\'' +
                ", " + coordinates +
                ", " + creationDate +
                ", " + studentsCount +
                ", " + expelledStudents +
                ", " + formOfEducation +
                ", " + semesterEnum +
                ", " + groupAdmin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup that = (StudyGroup) o;
        return studentsCount == that.studentsCount && expelledStudents == that.expelledStudents &&
                name.equals(that.name) && coordinates.equals(that.coordinates) && creationDate.equals(that.creationDate)
                && formOfEducation == that.formOfEducation && semesterEnum == that.semesterEnum &&
                groupAdmin.equals(that.groupAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates, creationDate, studentsCount, expelledStudents, formOfEducation,
                semesterEnum, groupAdmin);
    }

    @Override
    public int compareTo(StudyGroup o) {
        if (!(name.equals(o.name))) return name.compareTo(o.name);
        if (!(coordinates.equals(o.coordinates))) return coordinates.compareTo(o.coordinates);
        if (!(creationDate.equals(o.creationDate))) return creationDate.compareTo(o.creationDate);
        if (!(studentsCount==o.studentsCount)) return Integer.compare(studentsCount, o.studentsCount);
        if (!(expelledStudents==o.expelledStudents)) return Long.compare(expelledStudents,o.expelledStudents);
        if (!(Objects.equals(formOfEducation, o.formOfEducation)))
            return NullSafeComparator.compare(formOfEducation, o.formOfEducation);
        if (!(Objects.equals(semesterEnum, o.semesterEnum)))
            return NullSafeComparator.compare(semesterEnum, o.semesterEnum);
        return groupAdmin.compareTo(o.groupAdmin);
    }
}

