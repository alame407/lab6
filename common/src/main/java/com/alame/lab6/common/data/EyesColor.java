package com.alame.lab6.common.data;

import java.util.Arrays;

public enum EyesColor {
    RED,
    BLUE,
    BROWN;
    private static final EyesColor[] possibleValues = values();
    public static String possibleValues(){
        return Arrays.toString(possibleValues);
    }
    public static boolean constantExist(String string){
        for (EyesColor eyesColor: possibleValues){
            if (eyesColor.name().equals(string)) return true;
        }
        return false;
    }
}
