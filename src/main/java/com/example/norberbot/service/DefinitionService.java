package com.example.norberbot.service;

import com.example.norberbot.model.Definition;
import com.example.norberbot.repository.DefinitionRepository;
import com.example.norberbot.service.helpers.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefinitionService {

    private final DefinitionRepository definitionRepository;

    @Autowired
    public DefinitionService(DefinitionRepository definitionRepository) {
        this.definitionRepository = definitionRepository;
    }

    public boolean saveWord(String name, String definition)     {
        if(findWord(name.toLowerCase()) == null && HelperFunctions.checkStringLength(name)) {
            definitionRepository.save(new Definition(name.toLowerCase(),definition.toLowerCase()));
            return true;
        }
        return false;
    }

    public List<Definition> findWordsThatContains(String str) {
         if (HelperFunctions.checkStringLength(str)) {
             return definitionRepository
                     .findAll()
                     .stream()
                     .filter(entry -> entry.getWord().toLowerCase().contains((str.toLowerCase())))
                     .collect(Collectors.toList());
         }
         else {
             return null;
         }
     }

    public Definition findWord(String name) {
        return definitionRepository.findByWord(name);
    }
}
