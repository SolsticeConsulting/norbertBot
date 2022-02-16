package com.example.norberbot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "ideadefinitions")
@Entity
public class WordsDescriptions implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Palabra", nullable = false)
    private Word word;

    @Column(name = "Definicion")
    private String definition;

    @Column(name = "Ver")
    private String see;

    @Column(name = "Fuente")
    private String link;

    @Column(name = "Notas")
    private String other;

    public WordsDescriptions() {

    }

    public WordsDescriptions(String definition) {
        this.definition = definition;
    }

    public WordsDescriptions(Word word, String definition, String see, String link, String other) {
        this.word = word;
        this.definition = definition;
        this.see = see;
        this.link = link;
        this.other = other;
    }

    public Word getWord() {
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

    public void setWord(String name) {
        this.word = new Word(name);
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

    // @Override
    // public String toString() {
    // return " " + System.lineSeparator() +
    // ":pencil2: Palabra: " + word.getWord() + System.lineSeparator() +
    // ":book: Definicion: " + definition + System.lineSeparator() +
    // ((checkWord(link) == false) ? " " : ":link: Enlace: " + link +
    // System.lineSeparator()) +
    // ((checkWord(see) == false) ? " " : ":eyes: Ver: " + see +
    // System.lineSeparator()) +
    // ((checkWord(other) == false) ? " " : ":mag: Otros :" + other +
    // System.lineSeparator());

    // }
}