package com.example.desafio.services.impl;

import com.example.desafio.dto.ResponseAlunoDTO;
import com.example.desafio.entities.Aluno;
import com.example.desafio.mappers.AlunoMapper;
import com.example.desafio.repositories.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceImplTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoServiceImpl alunoService;


    @Test
    void shouldCallCalcularCrAlunos() {

        alunoService.calcularCrAlunos();


        verify(alunoRepository, times(1)).calcularCrAlunos();
        verifyNoMoreInteractions(alunoRepository);
    }


    @Test
    void shouldReturnMappedAlunoDTOList() {
        Aluno aluno1 = new Aluno();
        Aluno aluno2 = new Aluno();
        ResponseAlunoDTO dto1 = new ResponseAlunoDTO();
        ResponseAlunoDTO dto2 = new ResponseAlunoDTO();

        when(alunoRepository.findAll()).thenReturn(Arrays.asList(aluno1, aluno2));

        try (MockedStatic<AlunoMapper> mapperMock = mockStatic(AlunoMapper.class)) {
            mapperMock.when(() -> AlunoMapper.converterParaDto(aluno1)).thenReturn(dto1);
            mapperMock.when(() -> AlunoMapper.converterParaDto(aluno2)).thenReturn(dto2);


            List<ResponseAlunoDTO> result = alunoService.buscarAlunos();


            assertNotNull(result);
            assertEquals(2, result.size());
            assertTrue(result.containsAll(Arrays.asList(dto1, dto2)));
        }
    }

    @Test
    void shouldReturnEmptyListWhenNoAlunosExist() {

        when(alunoRepository.findAll()).thenReturn(Collections.emptyList());


        List<ResponseAlunoDTO> result = alunoService.buscarAlunos();


        assertNotNull(result);
        assertTrue(result.isEmpty());
    }


    @Test
    void shouldSaveAllAlunos() {

        Aluno a1 = new Aluno();
        Aluno a2 = new Aluno();
        Collection<Aluno> alunos = Arrays.asList(a1, a2);


        alunoService.salvarAlunos(alunos);


        verify(alunoRepository, times(1)).saveAll(alunos);
    }

    @Test
    void shouldSaveEmptyCollectionWithoutErrors() {

        Collection<Aluno> empty = Collections.emptyList();


        alunoService.salvarAlunos(empty);


        verify(alunoRepository, times(1)).saveAll(empty);
    }
}
