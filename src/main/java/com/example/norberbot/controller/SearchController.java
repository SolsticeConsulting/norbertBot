package com.example.norberbot.controller;

import com.example.norberbot.model.Definition;
import com.example.norberbot.model.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SearchController {

    private Map<Word,Definition> dictionary;

    public SearchController() {
        this.dictionary = new HashMap<Word,Definition>();
    }

    void createWord(String name) {
        dictionary.put(name,new ArrayList<>());
    }

    public List<String> addDescription(String word, String description){
        List<String> descriptions = findWordDescription(word);
        if(descriptions != null) {
            descriptions.add(description);
        }
        else {
            return null;
        }
        return descriptions;
    }

    public List<String> findWordDescription(String word){
        return dictionary.get(word);
    }

    void printDictionary(){
        dictionary.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
    }

    public List<String> findWordsThatContains(String str){
        return dictionary
                .keySet()
                .stream()
                .filter(entry -> entry.contains(str.toLowerCase()))
                .collect(Collectors.toList());
    }
}
