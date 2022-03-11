package com.example.norberbot.model;

import org.hibernate.annotations.Type;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Table(name = "calendar")
@Entity
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "text")
    @NotNull
    private String date;

    @Type(type = "text")
    private String name;

    @Type(type = "text")
    @NotNull
    private String description;

    public Calendar(@NotNull String date, String name, @NotNull String description) {
        this.date = date;
        this.name = name;
        this.description = description;
    }

    public Calendar() {
    }

    @NotNull
    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
