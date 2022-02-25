package com.example.norberbot.calendar;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Calendar {

    ArrayList<String> slackChannels = new ArrayList<String>() {
        {
            add("calendar");
            add("idea-bot");
        }
    };

    private Map<String, String> dates = new HashMap<String, String>() {
        {
            put("25-02", "Norber Did it Again!");
        }
    };

    @Scheduled(cron = "0 0/5 10 * * ?")
    public void test() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
        Date today = new Date();
        String date = sdf.format(today);

        if (dates.containsKey(date)) {
            slackChannels.forEach(channel -> {
                PublishingMessage.publishMessage(channel, dates.get(date));
            });
        }
    }
}
