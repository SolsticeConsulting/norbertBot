package com.example.schedulledTasks;

import com.example.norberbot.service.AnalyticsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AnalyticsSchedule {

    @Autowired
    AnalyticsService analyticsService;

    // @Scheduled(cron = "0 15 10 L * ?") último día del mes 10:15 AM
    @Scheduled(cron = "*/3 * * * *") // Test cada 3 minutos
    public void monthlyStats() {
        // post msg to channels
        // reset stats
    }
}
