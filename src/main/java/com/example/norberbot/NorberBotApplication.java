package com.example.norberbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.example.norberbot.servlets.slack")
public class NorberBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(NorberBotApplication.class, args);
    }

}
