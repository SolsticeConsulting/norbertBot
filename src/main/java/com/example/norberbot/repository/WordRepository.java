package com.example.norberbot.repository;

import com.example.norberbot.model.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends CrudRepository<Word, String> {
    Word findByName(String name);
}