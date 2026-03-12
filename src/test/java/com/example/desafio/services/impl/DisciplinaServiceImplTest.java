package com.example.desafio.services.impl;

import com.example.desafio.entities.Disciplina;
import com.example.desafio.repositories.DisciplinaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DisciplinaServiceImplTest {

    @Mock
    private DisciplinaRepository disciplinaRepository;

    @InjectMocks
    private DisciplinaServiceImpl disciplinaService;

    @Test
    void shouldSaveAllDisciplinas() {
        Disciplina d1 = new Disciplina();
        Disciplina d2 = new Disciplina();
        Collection<Disciplina> disciplinas = Arrays.asList(d1, d2);


        disciplinaService.salvarDisciplinas(disciplinas);


        verify(disciplinaRepository, times(1)).saveAll(disciplinas);
    }

    @Test
    void shouldSaveEmptyCollectionWithoutErrors() {

        Collection<Disciplina> empty = Collections.emptyList();


        disciplinaService.salvarDisciplinas(empty);


        verify(disciplinaRepository, times(1)).saveAll(empty);
        verifyNoMoreInteractions(disciplinaRepository);
    }

    @Test
    void shouldSaveSingleDisciplina() {

        Disciplina d = new Disciplina();
        Collection<Disciplina> single = Collections.singletonList(d);


        disciplinaService.salvarDisciplinas(single);


        verify(disciplinaRepository, times(1)).saveAll(single);
        verifyNoMoreInteractions(disciplinaRepository);
    }
}
