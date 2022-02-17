package com.example.norberbot.service;

import com.example.norberbot.model.Definition;
import com.example.norberbot.repository.DefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordService {

    private final DefinitionRepository definitionRepository;

    @Autowired
    public WordService(DefinitionRepository definitionRepository) {
        this.definitionRepository = definitionRepository;
    }

    public boolean saveWord(String name, String definition) {
        if (findWord(name)!=null) {
           definitionRepository.save(new Definition(name,definition));
            return true;
        }
        else {return false;}
    }

     public List<Definition> findWordsThatContains(String str) {
        return definitionRepository
                .findAll()
                .stream()
                .filter(entry -> entry.getWord().contains((str.toLowerCase())))
                .collect(Collectors.toList());
        }

    public Definition findWord(String name) {
            Definition wordFound = definitionRepository.findByWord(name);
            return wordFound;
    }
}
