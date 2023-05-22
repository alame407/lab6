package com.alame.lab6.common.data;

import java.util.Arrays;

public enum HairColor {
    GREEN,
    RED,
    ORANGE,
    WHITE;
    private static final HairColor[] possibleValues = values();
    public static String possibleValues(){
        return Arrays.toString(possibleValues);
    }
    public static boolean constantExist(String string){
        for (HairColor hairColor: possibleValues){
            if (hairColor.name().equals(string)) return true;
        }
        return false;
    }
}
