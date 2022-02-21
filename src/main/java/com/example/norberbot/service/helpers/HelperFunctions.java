package com.example.norberbot.service.helpers;

public class HelperFunctions {

    private static int strMinLength = 2;

    public static boolean checkStringLength(String str){
        return str.length() > strMinLength ? true : false;
    }
}
