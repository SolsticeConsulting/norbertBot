package com.example.norberbot.service;

import com.example.norberbot.model.Analytics;
import com.example.norberbot.model.Definition;
import com.example.norberbot.repository.AnalyticsRepository;
import com.example.norberbot.repository.DefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordService {

    private final DefinitionRepository definitionRepository;
    private final AnalyticsRepository analyticsRepository;
    private int strMinLength = 3;

    @Autowired
    public WordService(DefinitionRepository definitionRepository, AnalyticsRepository analyticsRepository) {
        this.definitionRepository = definitionRepository;
        this.analyticsRepository = analyticsRepository;
    }

    public boolean saveWord(String name, String definition) {
        if (findWord(name)!=null) {
           definitionRepository.save(new Definition(name.toLowerCase(),definition.toLowerCase()));
            return true;
        }
        else {return false;}
    }

    public List<Definition> findWordsThatContains(String str) {
         if (checkStringLength(str)) {
             return definitionRepository
                     .findAll()
                     .stream()
                     .filter(entry -> entry.getWord().contains((str.toLowerCase())))
                     .collect(Collectors.toList());
         }
         else {
             return null;
         }
     }

    public Definition findWord(String name) {
        if(!checkStringLength(name)) return null;

            Definition wordFound = definitionRepository.findByWord(name.toLowerCase());
            if(wordFound != null) {
                analyticsHandler(name);
                return wordFound;
            } else return null;

    }

    public List<Analytics> getAnalytics() {
        return analyticsRepository
                .findAll()
                .stream()
                .collect(Collectors.toList());
    }

    private boolean checkStringLength(String str){
        return str.length() > 2 ? true : false;
    }

    private void analyticsHandler(String name){
        Analytics analyticsFound = analyticsRepository.findByWord(name);
        if(analyticsFound == null) {
            Analytics newRegistry = new Analytics(name);
            newRegistry.updateQuantity();
            analyticsRepository.save(newRegistry);
        } else {
            analyticsFound.updateQuantity();
            analyticsRepository.save(analyticsFound);
        }
    }

}
