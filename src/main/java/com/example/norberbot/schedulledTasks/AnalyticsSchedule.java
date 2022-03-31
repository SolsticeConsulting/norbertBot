package com.example.norberbot.schedulledTasks;

import com.example.norberbot.handler.SlackHandler;
import com.example.norberbot.helper.AnswerHelper;
import com.example.norberbot.model.Analytics;
import com.example.norberbot.service.AnalyticsService;
import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AnalyticsSchedule extends SlackHandler {

    @Autowired
    AnalyticsService analyticsService;

    @Scheduled(cron = "0 15 10 L *  ?", zone = "GMT-3")
    public void monthlyStats() throws SlackApiException, IOException {
        List<Analytics> analytics = analyticsService.getAnalytics();

        StringBuilder builder = new StringBuilder();

        if (analytics.isEmpty()) {
            try {
                postCalendarMessage(Slack.getInstance().methods(), "latam-idea", AnswerHelper.getNotAnalyticsFounded());
            } catch (SlackApiException | IOException e) {
                e.printStackTrace();
            }
        } else {
            builder.append(AnswerHelper.getAnalyticsHeader());
            for (Analytics word : analytics) {
                builder.append(word.toString());
                builder.append("\n");
            }
            String message = builder.toString();
            try {
                postCalendarMessage(Slack.getInstance().methods(), "latam-idea", message);
            } catch (SlackApiException | IOException e) {
                e.printStackTrace();
            }
        }
        analyticsService.resetAnalytics();
    }
}
