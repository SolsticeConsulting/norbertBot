package com.example.norberbot.model;

import javax.persistence.*;

@Table(name = "ideadefinitions")
@Entity
public class Definition {

    @Id
    @Column(name = "Palabras")
    private String word;

    @Column(name = "Definicion")
    private String definition;

    @Column(name = "Ver")
    private String see;

    @Column(name = "Fuente")
    private String link;

    @Column(name = "Otros")
    private String other;

    public Definition() {

    }

    public Definition(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }

    public String getSee() {
        return see;
    }

    public String getLink() {
        return link;
    }

    public String getOther() {
        return other;
    }

    public boolean checkWord(String string) {
        return string != null && !string.equals("Empty") && !string.equals("");
    }

    @Override
    public String toString() {
        String message = "";
        return " " + System.lineSeparator() +
                ":pencil2: Palabra: " + word + System.lineSeparator() +
                ":book: Definicion: " + definition + System.lineSeparator() +
                ((!checkWord(link)) ? " " : ":link: Enlace: " + link + System.lineSeparator()) +
                ((!checkWord(see)) ? " " : ":eyes: Ver: " + see + System.lineSeparator()) +
                (!(checkWord(other)) ? " " : ":mag: Otros :" + other + System.lineSeparator());
        }
    }