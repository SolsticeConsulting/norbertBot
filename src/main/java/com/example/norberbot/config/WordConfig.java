package com.example.norberbot.config;

import com.example.norberbot.controller.BotController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WordConfig {

    @Autowired
    BotController botController;

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            botController.saveWord("violencia de genero","majin buu");
            botController.saveWord("violencia de netero","vencio al rey hormiga");
            botController.saveWord("goku","super saiyan 3");
            botController.saveWord("Forrest Gump","corría mucho");
            botController.saveWord("El oso Yogui","se afanaba las viandas en JellyStone");
            botController.saveWord("Gene Simmons","Lick it up");
        };
    }
}
