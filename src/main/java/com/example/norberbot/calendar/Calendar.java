package com.example.norberbot.calendar;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Calendar {

    private Map<String,String> dates = new HashMap<String, String>() {{
        put("24-02", "Norber flyes!");
    }};

    @Scheduled(cron = "0 * 14 * * ?")
    public void test(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
        Date today = new Date();

        String date = sdf.format(today);

        if(dates.containsKey(date))
        {
            System.out.println(dates.get(date));
        }

        //System.out.println("hola Norber, hoy es: " + date);
    }

}
