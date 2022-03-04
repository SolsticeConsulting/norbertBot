package com.example.norberbot.calendar;

import org.hibernate.annotations.Type;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="calendar")
@Entity
public class Calendar {

    @Type(type="text") @Column(length = 5) @NotNull
    private String date;
    @Id
    private String name;
    @Type(type="text") @NotNull
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
