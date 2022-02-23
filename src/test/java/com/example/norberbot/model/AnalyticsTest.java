package com.example.norberbot.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnalyticsTest {

    protected Analytics analytics;

    @BeforeEach
    void setUp() {
        analytics = new Analytics("test");
        analytics.updateQuantity();
    }

    @Test
    void updateQuantityEqualsTrue() {
        assertAll("Test quantity",
                () -> assertEquals(1,analytics.getQuantity(),"Update Quantity Doesnt Work"),
                () -> assertFalse(analytics.getQuantity() == 0));
    }

    @Test
    void resetQuantityEqualsZero() {
        analytics.resetQuantity();
        assertAll("Test reset quantity",
                () -> assertEquals(0,analytics.getQuantity(),"Update Quantity Doesnt Work"),
                () -> assertTrue(analytics.getQuantity() == 0));
    }
}