package com.example.norberbot.config;

import com.example.norberbot.model.Definition;
import com.example.norberbot.repository.AnalyticsRepository;
import com.example.norberbot.repository.DefinitionRepository;
import com.example.norberbot.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class WordConfig {
    @Autowired
    private WordService wordService;

    @Bean
    CommandLineRunner commandLineRunner(DefinitionRepository definitionRepository, AnalyticsRepository analyticsRepository){
        return args -> {
           Definition word1 = new Definition("violencia de genero","majin buu");
           Definition word2 = new Definition("violencia de netero","vencio al rey hormiga");

           definitionRepository.saveAll(List.of(word1,word2));

           wordService.findWord("violencia de netero");
           wordService.findWord("violencia de netero");
           wordService.findWord("violencia de genero");
           wordService.findWord("papa frita");

           wordService.findWordsThatContains("violencia")
                           .stream()
                           .forEach(word -> System.out.println(word));

            wordService.getAnalytics()
                    .stream()
                    .forEach(word -> System.out.println(word));

        };
    }
}
