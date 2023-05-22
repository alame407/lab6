package com.alame.lab6.common.data;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Person implements Comparable<Person>, Serializable {
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final LocalDate birthday; //Поле не может быть null
    private final EyesColor eyeColor; //Поле не может быть null
    private final HairColor hairColor; //Поле может быть null
    private final Country nationality; //Поле не может быть null
    public Person(String name, LocalDate birthday, EyesColor eyeColor, HairColor hairColor, Country nationality){
        this.name = name;
        this.birthday = birthday;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public Country getNationality() {
        return nationality;
    }

    public EyesColor getEyeColor() {
        return eyeColor;
    }

    public HairColor getHairColor() {
        return hairColor;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", eyeColor=" + eyeColor +
                ", hairColor=" + hairColor +
                ", nationality=" + nationality +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name) && birthday.equals(person.birthday) && eyeColor == person.eyeColor
                && hairColor == person.hairColor && nationality == person.nationality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday, eyeColor, hairColor, nationality);
    }

    @Override
    public int compareTo(Person o) {
        if (!(name.equals(o.name))) return name.compareTo(o.name);
        if (!(birthday.equals(o.birthday))) return birthday.compareTo(o.birthday);
        if (!(eyeColor.equals(o.eyeColor))) return eyeColor.compareTo(o.eyeColor);
        if(!(Objects.equals(hairColor, o.hairColor))) return NullSafeComparator.compare(hairColor, o.hairColor);
        return nationality.compareTo(o.nationality);
    }
}
