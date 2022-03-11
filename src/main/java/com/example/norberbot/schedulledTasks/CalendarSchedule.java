package com.example.norberbot.schedulledTasks;

import com.example.norberbot.handler.SlackHandler;
import com.example.norberbot.model.Calendar;
import com.example.norberbot.service.CalendarService;
import com.slack.api.Slack;
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
public class CalendarSchedule extends SlackHandler {

    @Autowired
    private CalendarService calendarService;

    // @Scheduled(cron = "0 15 10 * * ?", zone = "GMT-3") dejar este luego del test
    @Scheduled(cron = "*/2 * * * *", zone = "GMT-3") // test c/2 minutos
    public void checkDate() {
        List<String> slackChannels = new ArrayList<>(Arrays.asList(System.getenv("MY_CHANNELS").split(",")));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
        Date today = new Date();
        String date = sdf.format(today);
        List<Calendar> foundDates = calendarService.FindByDate(date);
        if (!foundDates.isEmpty()) {
            slackChannels.forEach(channel -> {
                foundDates.forEach(datefound -> {
                    try {
                        postCalendarMessage(Slack.getInstance().methods(), channel, datefound.getDescription());
                    } catch (SlackApiException | IOException e) {
                        e.printStackTrace();
                    }
                });
            });
        }
    }
}
