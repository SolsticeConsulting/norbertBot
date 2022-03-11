package com.example.norberbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.norberbot.model.Calendar;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, String> {
    List<Calendar> findByDate(String date);
}
