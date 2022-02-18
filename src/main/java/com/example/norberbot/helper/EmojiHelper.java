package com.example.norberbot.helper;

import com.google.common.collect.ImmutableMap;

public class EmojiHelper {

    private static final ImmutableMap<Integer, String> NUMBERS = new ImmutableMap.Builder<Integer, String>()
            .put(1, ":one: ")
            .put(2, ":two: ")
            .put(3, ":three: ")
            .put(4, ":four: ")
            .put(5, ":five: ")
            .put(6, ":six: ")
            .put(7, ":seven: ")
            .put(8, ":eight: ")
            .put(9, ":nine: ").build();

    public static String getEmojiNumber(int number){
        return NUMBERS.get(number);
    }
}
