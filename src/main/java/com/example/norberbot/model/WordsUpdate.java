package com.example.norberbot.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wordsupdates")
public class WordsUpdate {

    @Id
    private String palabra;

    public WordsUpdate() {
    }

    public void setPalabra(String id) {
        this.palabra = id;
    }

    public String getPalabra() {
        return palabra;
    }

    public WordsUpdate(String palabra) {
        this.palabra = palabra;
    }

    @Override
    public String toString() {
        return "WordsUpdate{" +
                "palabra='" + palabra + '\'' +
                '}';
    }
}