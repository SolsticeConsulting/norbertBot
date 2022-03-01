package com.example.norberbot.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Table(name = "ideadefinitions")
@Entity
public class Definition {

    @Id
    @Column(name = "Palabras")
    private String word;

    @Column(name = "Definicion")
    @Type(type = "text")
    private String definition;

    @Column(name = "Ver")
    @Type(type = "text")
    private String see;

    @Column(name = "Fuente")
    @Type(type = "text")
    private String link;

    @Column(name = "Otros")
    @Type(type = "text")
    private String other;

    public Definition() {

    }

    public Definition(String word, String definition, String see, String link, String other) {
        this.word = word;
        this.definition = definition;
        this.see = see;
        this.link = link;
        this.other = other;
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
                ((!checkWord(other)) ? " " : ":mag: Otros :" + other + System.lineSeparator());
        }
    }