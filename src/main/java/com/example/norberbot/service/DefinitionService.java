package com.example.norberbot.service;

import com.example.norberbot.model.Definition;
import com.example.norberbot.repository.DefinitionRepository;
import com.example.norberbot.helper.StringHelper;
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

<<<<<<< HEAD
=======
    public boolean saveWord(String name, String definition)     {
        if(findWord(name.toLowerCase()) == null && StringHelper.checkStringLength(name)) {
            definitionRepository.save(new Definition(name.toLowerCase(),definition.toLowerCase()));
            return true;
        }
        return false;
    }

>>>>>>> origin
    public List<Definition> findWordsThatContains(String str) {
         if (StringHelper.checkStringLength(str)) {
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
