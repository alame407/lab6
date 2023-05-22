package com.alame.lab6.common.data;

import java.util.Arrays;

public enum FormOfEducation {
    DISTANCE_EDUCATION,
    FULL_TIME_EDUCATION,
    EVENING_CLASSES;
    private static final FormOfEducation[] possibleValues = values();
    public static String possibleValues(){
        return Arrays.toString(possibleValues);
    }
    public static boolean constantExist(String string){
        for (FormOfEducation formOfEducation: possibleValues){
            if (formOfEducation.name().equals(string)) return true;
        }
        return false;
    }

}
