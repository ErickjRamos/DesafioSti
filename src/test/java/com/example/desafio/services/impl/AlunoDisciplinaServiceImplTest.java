package com.example.desafio.services.impl;

import com.example.desafio.entities.AlunoDisciplina;
import com.example.desafio.repositories.AlunoDisciplinaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoDisciplinaServiceImplTest {

    @Mock
    private AlunoDisciplinaRepository alunoDisciplinaRepository;

    @InjectMocks
    private AlunoDisciplinaServiceImpl alunoDisciplinaService;

    @Test
    void shouldSaveAllAlunoDisciplinas() {

        AlunoDisciplina ad1 = new AlunoDisciplina();
        AlunoDisciplina ad2 = new AlunoDisciplina();
        List<AlunoDisciplina> list = Arrays.asList(ad1, ad2);


        alunoDisciplinaService.salvarAlunoDisciplinas(list);


        verify(alunoDisciplinaRepository, times(1)).saveAll(list);
    }

    @Test
    void shouldSaveEmptyListWithoutErrors() {

        List<AlunoDisciplina> emptyList = Collections.emptyList();


        alunoDisciplinaService.salvarAlunoDisciplinas(emptyList);


        verify(alunoDisciplinaRepository, times(1)).saveAll(emptyList);
    }

    @Test
    void shouldSaveSingleAlunoDisciplina() {

        AlunoDisciplina ad = new AlunoDisciplina();
        List<AlunoDisciplina> list = Collections.singletonList(ad);


        alunoDisciplinaService.salvarAlunoDisciplinas(list);

        verify(alunoDisciplinaRepository, times(1)).saveAll(list);
        verifyNoMoreInteractions(alunoDisciplinaRepository);
    }
}
