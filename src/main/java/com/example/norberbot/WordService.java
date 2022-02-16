package com.example.norberbot;

import java.util.*;
import java.util.stream.Collectors;

import com.example.norberbot.model.Word;
import com.example.norberbot.model.WordsDescriptions;

public class WordService {

    private List<Word> dictionary;

    public WordService() {
        this.dictionary = new ArrayList<Word>();
    }

    boolean saveWord(Word word) {
        if (!dictionary.contains(word)) {
            this.dictionary.add(word);
            return true;
        }
        return false;
    }

    public List<WordsDescriptions> findWordDescriptions(String name) {
        Word wordFound = findWord(name);
        if (wordFound != null) {
            List<WordsDescriptions> descriptions = wordFound.getDescriptions()
                    .stream()
                    .collect(Collectors.toList());
            return descriptions;
        } else {
            return null;
        }
    }

    void printDictionary() {
        // wordRepository.entrySet().forEach(entry -> {
        // System.out.println(entry.getKey() + " " + entry.getValue());
        // });
        this.dictionary.forEach(entry -> {
            Set<WordsDescriptions> description = entry.getDescriptions();
            System.out.println("\nPalabra: " + entry);
            System.out.println("===================");
            description.forEach(def -> {
                System.out.println("Definicion: " + def.getDefinition());
            });
        });
    }

    // public List<String> findWordsThatContains(String str) {
    // return wordRepository
    // .keySet()
    // .stream()
    // .filter(entry -> entry.contains(str.toLowerCase()))
    // .collect(Collectors.toList());
    // }

    public Word findWord(String name) {

        Word wordFound = dictionary.stream().filter(item -> item.getWord().equals(name)).findFirst()
                .orElse(null);
        return wordFound != null ? wordFound : null;
    }
}
