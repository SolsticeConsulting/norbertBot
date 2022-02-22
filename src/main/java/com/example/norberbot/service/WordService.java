package com.example.norberbot.service;

import com.example.norberbot.model.Definition;
import com.example.norberbot.model.repository.DefinitionRepository;
import one.util.streamex.StreamEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {

    private final DefinitionRepository definitionRepository;

    @Autowired
    public WordService(DefinitionRepository definitionRepository) {
        this.definitionRepository = definitionRepository;
    }

    public boolean saveWord(String name, String definition) {
        if (findWord(name) != null) {
            definitionRepository.save(new Definition(name, definition));
            return true;
        } else {
            return false;
        }
    }

    public List<Definition> getAllWords(){
        return definitionRepository.findAll();
    }

    public List<Definition> findWordsThatContains(String str) {
        return StreamEx.of(definitionRepository.findAll())
                .filter(entry -> entry.getWord().contains((str.toLowerCase())))
                .toList();
    }

    public Definition findWord(String name) {
        Definition wordFound = definitionRepository.findByWord(name);
        return wordFound;
    }
}
