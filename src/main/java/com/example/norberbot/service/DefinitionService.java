package com.example.norberbot.service;

import com.example.norberbot.model.Definition;
import com.example.norberbot.repository.DefinitionRepository;
import com.example.norberbot.helper.StringHelper;
import one.util.streamex.StreamEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class DefinitionService {

    private final DefinitionRepository definitionRepository;

    @Autowired
    public DefinitionService(DefinitionRepository definitionRepository) {
        this.definitionRepository = definitionRepository;
    }

    public List<Definition> findWordsThatContains(String str) {
         if (StringHelper.checkStringLength(str)) {
             return StreamEx.of(definitionRepository.findAll())
                     .filter(entry -> entry.getWord().toLowerCase(Locale.ENGLISH).contains(str.toLowerCase(Locale.ENGLISH)))
                     .toList();
         }
         return List.of();
     }

    public Definition findWord(String name) {
        return definitionRepository.findByWord(name);
    }

    public List<Definition> getAllWords(){
        return definitionRepository.findAll();
    }
}


