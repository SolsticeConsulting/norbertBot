package com.example.norberbot.calendar;

import com.example.norberbot.handler.SlackHandler;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class CalendarHandler extends SlackHandler {

    @Autowired
    CalendarService calendarService;

    @Scheduled(cron = "0 0/5 18 * * ?")
    public void test() {
        List<String> slackChannels = new ArrayList<>(Arrays.asList(System.getenv("MY_CHANNELS").split(",")));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
        Date today = new Date();
        String date = sdf.format(today);
        List<Calendar> foundDates = calendarService.FindByDate(date);
        if (!foundDates.isEmpty()) {
            slackChannels.forEach(channel -> {
                foundDates.forEach(datefound -> {
                    try {
                        postCalendarMessage(Slack.getInstance().methods(), channel, datefound.getDate());
                    } catch (SlackApiException | IOException e) {
                        e.printStackTrace();
                    }
                });
            });
        }
    }
}
