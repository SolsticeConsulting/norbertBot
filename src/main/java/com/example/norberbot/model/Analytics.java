package com.example.norberbot.model;

import javax.persistence.*;

@Entity
@Table(name = "searchedwords")
public class Analytics {

    @Id
    private String word;

    private int quantity;

    public Analytics() {
    }

    public Analytics(String word) {
        this.word = word;
        this.quantity = 0;
    }

    public void updateQuantity(){
        this.quantity += 1;
    }

    public void resetQuantity(){
        this.quantity = 0;
    }

    @Override
    public String toString() {
        return ":book: " + "'" + word + "'" + " cantidad de veces buscada :mag: :  " + quantity;
    }
}