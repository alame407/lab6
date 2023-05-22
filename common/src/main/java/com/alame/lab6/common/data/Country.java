package com.alame.lab6.common.data;

import java.util.Arrays;

public enum Country {
    RUSSIA,
    VATICAN,
    SOUTH_KOREA;
    private static final Country[] possibleValues = values();
    public static String possibleValues(){
        return Arrays.toString(possibleValues);
    }
    public static boolean constantExist(String string){
        for (Country country: possibleValues){
            if (country.name().equals(string)) return true;
        }
        return false;
    }
}
