package com.example.norberbot.service;

import com.example.norberbot.model.Word;
import com.example.norberbot.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    boolean saveWord(Word word) {
        if (findWord(word.getName()) != null) {
            this.wordRepository.save(word);
            return true;
        }
        return false;
    }

/*    public List<WordsDescriptions> findWordDescriptions(String name) {
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
    }*/

    // public List<String> findWordsThatContains(String str) {
    // return wordRepository
    // .keySet()
    // .stream()
    // .filter(entry -> entry.contains(str.toLowerCase()))
    // .collect(Collectors.toList());
    // }

    public Word findWord(String name) {
        Word wordFound = wordRepository.findByName(name);
        return wordFound != null ? wordFound : null;
    }
}
