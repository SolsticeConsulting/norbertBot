package com.example.norberbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class NorberBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(NorberBotApplication.class, args);

/*        WordService dictionary = new WordService();

        Word word1 = new Word("violencia de genero");
        word1.setDescriptions("majin buu");
        word1.setDescriptions("kid buu");

        Word word2 = new Word("violencia de netero");
        word2.setDescriptions("vencio al rey hormiga");
        word2.setDescriptions("most badass");

        dictionary.saveWord(word1);
        dictionary.saveWord(word2);

        dictionary.printDictionary();

        List<WordsDescriptions> definitions = dictionary.findWordDescriptions("violencia de netero");

        definitions.forEach(def -> System.out.println(def.getDefinition()));*/

        // System.out.println(dictionary.findWordsThatContains("violencia"));
    }

}
