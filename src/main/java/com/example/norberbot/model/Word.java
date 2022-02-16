package com.example.norberbot.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// @Entity
// @Table(name = "ideadefinitions")
public class Word {

    // @Id
    // @Column(name = "palabras", unique = true)
    private String name;

    // @OneToMany(mappedBy = "word", fetch = FetchType.LAZY, cascade =
    // CascadeType.ALL)
    private Set<WordsDescriptions> descriptions;

    public Word(String name) {
        this.name = name;
        this.descriptions = new HashSet<>();
    }

    public Word() {

    }

    public void setWord(String name) {
        this.name = name;
    }

    public String getWord() {
        return name;
    }

    public void setDescriptions(String definition) {
        WordsDescriptions description = new WordsDescriptions(definition);
        descriptions.add(description);
    }

    public Set<WordsDescriptions> getDescriptions() {
        return this.descriptions;
    }

    @Override
    public String toString() {
        return name + System.lineSeparator();

    }
}
