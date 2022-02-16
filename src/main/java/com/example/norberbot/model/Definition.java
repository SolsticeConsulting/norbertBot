package com.example.norberbot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    public void setWord(String word) {
        this.word = word;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setSee(String see) {
        this.see = see;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setOther(String other) {
        this.other = other;
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
                ((checkWord(link) == false) ? " " : ":link: Enlace: " + link + System.lineSeparator()) +
                ((checkWord(see) == false) ? " " : ":eyes: Ver: " + see + System.lineSeparator()) +
                ((checkWord(other) == false) ? " " : ":mag: Otros :" + other + System.lineSeparator());

    }
}