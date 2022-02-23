package com.example.norberbot.service;

import com.example.norberbot.model.Definition;
import com.example.norberbot.repository.DefinitionRepository;
import com.example.norberbot.service.helpers.HelperFunctions;
import one.util.streamex.StreamEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefinitionService {

    private final DefinitionRepository definitionRepository;

    @Autowired
    public DefinitionService(DefinitionRepository definitionRepository) {
        this.definitionRepository = definitionRepository;
    }

    public List<Definition> findWordsThatContains(String str) {
         if (HelperFunctions.checkStringLength(str)) {
             return StreamEx.of(definitionRepository.findAll())
                     .filter(entry -> entry.getWord().toLowerCase().contains(str.toLowerCase()))
                     .toList();
         }
         else {
             return null;
         }
     }

    public Definition findWord(String name) {
        return definitionRepository.findByWord(name);
    }
}
