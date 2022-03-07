package com.example.norberbot.calendar;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;

    @Autowired
    public CalendarService(CalendarRepository calendarRepository){
        this.calendarRepository = calendarRepository;
    }

    public List<Calendar> FindByDate(String date){
       return calendarRepository.findByDate(date);
    }

    public void saveDate(@NotNull String date, @NotNull String name, @NotNull String description){
        calendarRepository.save(new Calendar(date,name,description));
    }

}
