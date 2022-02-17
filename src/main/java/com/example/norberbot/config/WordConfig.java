package com.example.norberbot.config;

import com.example.norberbot.model.Definition;
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
    CommandLineRunner commandLineRunner(DefinitionRepository repository){
        return args -> {
            Definition word1 = new Definition("violencia de genero","majin buu");
            Definition word2 = new Definition("violencia de netero","vencio al rey hormiga");

            repository.saveAll(List.of(word1,word2));
            System.out.println(wordService.findWord("violencia de netero"));
            System.out.println(wordService.findWord("papa frita"));
            boolean test1 = wordService.saveWord("sacapuntas metalico", "para lapiz de papel");
            boolean test2 = wordService.saveWord("violencia de netero","vencio al rey hormiga");
            System.out.println("test1: " + test1 + "y test2:" + test2);

            wordService.findWordsThatContains("teta")
                    .stream()
                    .forEach(word -> {
                        System.out.println(word);
                    });


        };
    }
}
