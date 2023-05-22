package com.alame.lab6.common.parsers;


import com.alame.lab6.common.data.CoordinatesValidator;
import com.alame.lab6.common.exceptions.IncorrectElementFieldException;

/**
 * class for parsing coordinates from string
 */
public class CoordinatesParser {
    private final CoordinatesValidator coordinatesValidator;

    public CoordinatesParser(CoordinatesValidator coordinatesValidator) {
        this.coordinatesValidator = coordinatesValidator;
    }

    /**
     * parse x from string
     * @param s - string to parse
     * @return x
     * @throws IncorrectElementFieldException if x is not valid
     */
    public Long parseX(String s) throws IncorrectElementFieldException {
        try{
            Long x = Long.parseLong(s);
            if (coordinatesValidator.validX(x)) return x;
            throw new IncorrectElementFieldException("Координата x должна быть целым числом");
        }
        catch (NumberFormatException e){
            throw new IncorrectElementFieldException("Координата x должна быть целым числом");
        }
    }

    /**
     * parse y from string
     * @param s - string to parse
     * @return y
     * @throws IncorrectElementFieldException if y is not valid
     */
    public float parseY(String s) throws IncorrectElementFieldException {
        try{
            float y = Float.parseFloat(s);
            if (coordinatesValidator.validY(y)) return y;
            throw new IncorrectElementFieldException("Координата y должна быть дробным числом числом типа float");
        }
        catch (NumberFormatException | NullPointerException e){
            throw new IncorrectElementFieldException("Координата y должна быть дробным числом числом типа float");
        }
    }
}
