package com.example.norberbot.service;

import com.example.norberbot.model.Definition;
import com.example.norberbot.repository.DefinitionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DefinitionServiceTest {

    @Mock
    DefinitionRepository definitionRepository;

    @InjectMocks
    DefinitionService definitionService;

    protected Definition word;
    protected List<Definition> wordList;

    @BeforeEach
    void setUp() {
        word = new Definition("name","definition",null,null,null);
        wordList = new ArrayList<>();
    }

    @Test
    void findWordsThatContainsNameReturnsListWithValues() {
        wordList.add(word);
        wordList.add(new Definition("Gene Simmons","tested",null,null,null));
        wordList.add(new Definition("violencia de genero","tested",null,null,null));

        given(definitionRepository
                .findAll()
                .stream()
                .filter(entry -> entry.getWord().contains((anyString().toLowerCase())))
                .collect(Collectors.toList())).willReturn(wordList);

        List<Definition> wordsFounded = definitionService.findWordsThatContains("gene");

        then(definitionRepository).should().findAll();
        assertEquals(2,wordsFounded.size());
    }

    @Test
    void findWordsThatContainsNameReturnsNull() {
        List<Definition> wordsFounded = definitionService.findWordsThatContains("na");

        verify(definitionRepository,never()).findAll();
        assertNull(wordsFounded,"Expected null value");
    }

    @Test
    void findWordReturnsWord() {
        given(definitionRepository.findByWord(anyString())).willReturn(word);

        Definition wordFounded = definitionService.findWord(anyString());

        then(definitionRepository).should().findByWord(anyString());
        assertEquals("name",wordFounded.getWord());
    }

    @Test
    void findWordReturnsNull() {
        given(definitionRepository.findByWord(anyString())).willReturn(null);

        Definition wordFounded = definitionService.findWord(anyString());

        then(definitionRepository).should().findByWord(anyString());
        assertEquals(null,wordFounded);
    }

}