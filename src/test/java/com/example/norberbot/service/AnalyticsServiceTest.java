package com.example.norberbot.service;

import com.example.norberbot.model.Analytics;
import com.example.norberbot.repository.AnalyticsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AnalyticsServiceTest {

    @Mock
    AnalyticsRepository analyticsRepository;

    @InjectMocks
    AnalyticsService analyticsService;

    protected Analytics analytics;
    protected List<Analytics> returnList;

    @BeforeEach
    void setUp() {
        analytics = new Analytics("name");
        returnList = new ArrayList<>();
    }

    @Test
    void getAnalyticsReturnsListWithOneRegistry() {
        //given
        returnList.add(analytics);
        given(analyticsRepository.findAll()).willReturn(returnList);

        //when
        List<Analytics> analyticsFound = analyticsService.getAnalytics();

        //then
        then(analyticsRepository).should().findAll();
        assertEquals(analyticsFound.size(),1);
    }

    @Test
    void analyticsHandlerSavesAndUpdatesNewWord() {
        //given
        given(analyticsRepository.findByWord("name")).willReturn(analytics);

        //when
        analyticsService.analyticsHandler("name");

        //then
        then(analyticsRepository).should().findByWord("name");
        assertEquals(analytics.getQuantity(),1,"Analytics Quantity is wrong");
    }


}