package com.example.norberbot.controller;

import com.example.norberbot.model.Analytics;
import com.example.norberbot.model.Definition;
import com.example.norberbot.service.AnalyticsService;
import com.example.norberbot.service.DefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BotController {

    private DefinitionService definitionService;
    private AnalyticsService analyticsService;

    @Autowired
    public BotController(DefinitionService definitionService, AnalyticsService analyticsService){
        this.definitionService = definitionService;
        this.analyticsService = analyticsService;
    }

    public List<Definition> findWordsThatContains(String str){
        return definitionService.findWordsThatContains(str);
    }

    public Definition findWord(String name){
        Definition wordFound = definitionService.findWord(name);
        if(wordFound != null) {
            analyticsService.analyticsHandler(name);
            return wordFound;
        } else return null;
    }

    public List<Analytics> getAnalytics(){
        return analyticsService.getAnalytics();
    }

    public void analyticsHandler(String name){
        analyticsService.analyticsHandler(name);
    }

}
