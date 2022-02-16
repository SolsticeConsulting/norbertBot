package com.example.norberbot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ideadefinitions")
public class AllDefinitions {

    @Id
    @Column(name = "palabras")
    private String word;


    public AllDefinitions(String word) {
    }

    public AllDefinitions() {

    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return word + System.lineSeparator();

    }
}
