package com.example.norberbot.repository;

import com.example.norberbot.model.Analytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyticsRepository extends JpaRepository<Analytics, String> {
    Analytics findByWord(String word);
}
