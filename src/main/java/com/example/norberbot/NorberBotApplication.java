package com.example.norberbot;

import com.example.norberbot.controller.SearchController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class NorberBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(NorberBotApplication.class, args);

        SearchController searchController = new SearchController();
        searchController.createWord("violencia de genero");
        searchController.addDescription("violencia de genero","majin buu");
        searchController.addDescription("violencia de genero","kid buu");

        searchController.createWord("violencia de netero");
        searchController.addDescription("violencia de netero","vencio al rey hormiga");
        searchController.addDescription("violencia de netero","most badass");

        searchController.printDictionary();
        System.out.println(searchController.findWordDescription("violencia de netero").size());

        System.out.println(searchController.findWordsThatContains("violencia"));
    }

}
