package com.alame.lab6.common.data;

import java.util.Arrays;

public enum Semester {
    FIRST,
    SIXTH,
    SEVENTH,
    EIGHTH;
    private static final Semester[] possibleValues = values();
    public static String possibleValues(){
        return Arrays.toString(possibleValues);
    }
    public static boolean constantExist(String string){
        for (Semester semester: possibleValues){
            if (semester.name().equals(string)) return true;
        }
        return false;
    }
}
