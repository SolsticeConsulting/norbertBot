package com.example.norberbot.service;

import com.example.norberbot.model.Analytics;
import com.example.norberbot.repository.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    AnalyticsRepository analyticsRepository;

    @Autowired
    public AnalyticsService(AnalyticsRepository analyticsRepository) {
        this.analyticsRepository = analyticsRepository;
    }

    public List<Analytics> getAnalytics() {
        return analyticsRepository
                .findAll()
                .stream()
                .collect(Collectors.toList());
    }

    public void analyticsHandler(String name){
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