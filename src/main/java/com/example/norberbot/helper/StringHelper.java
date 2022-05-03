package com.example.norberbot.helper;

public class StringHelper {

    private static final int STR_MIN_LENGTH = 2;

    public static boolean checkStringLength(String str){
        return str.length() > STR_MIN_LENGTH;
    }
}
