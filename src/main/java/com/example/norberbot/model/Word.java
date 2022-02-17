/*
package com.example.norberbot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "words")
public class Word implements Serializable {

    @Id
    @Column(name = "palabras", unique = true)
    private String name;

    @OneToMany(mappedBy = "word", fetch = FetchType.LAZY, cascade =
    CascadeType.ALL)
    private Set<WordsDescriptions> descriptions;

    public Word(String name) {
        this.name = name;
        this.descriptions = new HashSet<>();
    }

    public Word() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
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
*/
