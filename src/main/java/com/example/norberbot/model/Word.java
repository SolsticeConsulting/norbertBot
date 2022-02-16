package com.example.norberbot.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "searchedwords")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "palabra")
    private String word;

    private Integer quantity;

    private Date date;

    public Word(String word) {
        this.word = word;
    }

    public Word(Integer id, String word) {
        this.id = id;
        this.word = word;
    }

    public Word() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return ":book: " + "'" + word + "'" + " cantidad de veces buscada :mag: :  " + quantity;
    }
}