package com.alame.lab6.common.parsers;

import com.alame.lab6.common.data.Country;
import com.alame.lab6.common.data.EyesColor;
import com.alame.lab6.common.data.HairColor;
import com.alame.lab6.common.data.PersonValidator;
import com.alame.lab6.common.exceptions.IncorrectElementFieldException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * class for parsing person from string
 */
public class PersonParser {
    private final PersonValidator personValidator;

    public PersonParser(PersonValidator personValidator) {
        this.personValidator = personValidator;
    }

    /**
     * parse name from string
     * @param s - string to parse
     * @return name
     * @throws IncorrectElementFieldException if name is not valid
     */
    public String parseName(String s) throws IncorrectElementFieldException {
        if (personValidator.validName(s)) return s;
        throw new IncorrectElementFieldException("Имя не должно быть null или равняться пустой строке");
    }

    /**
     * parse eyesColor from string
     * @param s - string to parse
     * @return eyesColor
     * @throws IncorrectElementFieldException if eyesColor is not valid
     */
    public EyesColor parseEyesColor(String s) throws IncorrectElementFieldException {
        if (! (personValidator.validEyeColor(s))) throw new IncorrectElementFieldException("цвет глаз должен быть " +
                "одним из " + EyesColor.possibleValues());
        if (s==null) return null;
        return EyesColor.valueOf(s);
    }

    /**
     * parse hairColor from string
     * @param s - string to parse
     * @return hairColor
     * @throws IncorrectElementFieldException if hairColor is not valid
     */
    public HairColor parseHairColor(String s) throws IncorrectElementFieldException {
        if (!(personValidator.validHairColor(s))) throw new IncorrectElementFieldException("цвет волос должен быть " +
                "одним из " + HairColor.possibleValues());
        if (s==null) return null;
        return HairColor.valueOf(s);
    }

    /**
     * parse country from string
     * @param s - string to parse
     * @return country
     * @throws IncorrectElementFieldException if country is not valid
     */
    public Country parseCountry(String s) throws IncorrectElementFieldException {
        if(!(personValidator.validNationality(s))) throw new IncorrectElementFieldException("национальность должна " +
                "быть одной из " + Country.possibleValues());
        if (s==null) return null;
        return Country.valueOf(s);
    }

    /**
     * parse birthday from string
     * @param s - string to parse
     * @return birthday
     * @throws IncorrectElementFieldException if birthday is not valid
     */
    public LocalDate parseBirthday(String s) throws IncorrectElementFieldException{
        try {
            LocalDate birthday = LocalDate.parse(s);
            if (personValidator.validBirthday(birthday)) return birthday;
            throw new IncorrectElementFieldException("день рождение должен быть датой в формате гггг-мм-дд");
        }
        catch (DateTimeParseException | NullPointerException e){
            throw new IncorrectElementFieldException("день рождение должен быть датой в формате гггг-мм-дд");
        }

    }
}
