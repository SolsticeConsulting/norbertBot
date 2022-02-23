package com.example.norberbot.model.repository;

import com.example.norberbot.model.Definition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefinitionRepository extends JpaRepository<Definition, String> {
    Definition findByWord(String word);
}