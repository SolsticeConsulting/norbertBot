package com.example.norberbot.schedulledTasks;

import com.example.norberbot.handler.SlackHandler;
import com.example.norberbot.model.Analytics;
import com.example.norberbot.service.AnalyticsService;
import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class AnalyticsSchedule extends SlackHandler {

    @Autowired
    AnalyticsService analyticsService;

    @Scheduled(cron = "0 15 10 L *  ?", zone = "GMT-3")
    public void monthlyStats() throws SlackApiException, IOException {
        String channel = "idea-bot";// test

        List<Analytics> analytics = analyticsService.getAnalytics();
        StringBuilder builder = new StringBuilder();

        for (Analytics word : analytics) {
            builder.append(word.toString());
            builder.append("\n");
        }
        String msg = builder.toString();

        postCalendarMessage(Slack.getInstance().methods(), channel, msg);
        // reset stats
        analyticsService.resetAnalytics();

    }
}
