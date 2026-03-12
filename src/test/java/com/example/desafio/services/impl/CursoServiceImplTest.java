package com.example.desafio.services.impl;

import com.example.desafio.dto.ResponseCursoDTO;
import com.example.desafio.entities.Curso;
import com.example.desafio.mappers.CursoMapper;
import com.example.desafio.repositories.CursoRepository;
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
class CursoServiceImplTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoServiceImpl cursoService;


    @Test
    void shouldCallCalcularCrCursos() {

        cursoService.calcularCrCursos();


        verify(cursoRepository, times(1)).calcularCrCursos();
        verifyNoMoreInteractions(cursoRepository);
    }


    @Test
    void shouldReturnMappedCursoDTOList() {

        Curso curso1 = new Curso();
        Curso curso2 = new Curso();
        ResponseCursoDTO dto1 = new ResponseCursoDTO();
        ResponseCursoDTO dto2 = new ResponseCursoDTO();

        when(cursoRepository.findAll()).thenReturn(Arrays.asList(curso1, curso2));

        try (MockedStatic<CursoMapper> mapperMock = mockStatic(CursoMapper.class)) {
            mapperMock.when(() -> CursoMapper.converterParaDto(curso1)).thenReturn(dto1);
            mapperMock.when(() -> CursoMapper.converterParaDto(curso2)).thenReturn(dto2);


            List<ResponseCursoDTO> result = cursoService.buscarCursos();


            assertNotNull(result);
            assertEquals(2, result.size());
            assertTrue(result.containsAll(Arrays.asList(dto1, dto2)));
        }
    }

    @Test
    void shouldReturnEmptyListWhenNoCursosExist() {

        when(cursoRepository.findAll()).thenReturn(Collections.emptyList());


        List<ResponseCursoDTO> result = cursoService.buscarCursos();


        assertNotNull(result);
        assertTrue(result.isEmpty());
    }


    @Test
    void shouldSaveAllCursos() {

        Curso c1 = new Curso();
        Curso c2 = new Curso();
        Collection<Curso> cursos = Arrays.asList(c1, c2);


        cursoService.salvarCursos(cursos);


        verify(cursoRepository, times(1)).saveAll(cursos);
    }

    @Test
    void shouldSaveEmptyCollectionWithoutErrors() {

        Collection<Curso> empty = Collections.emptyList();


        cursoService.salvarCursos(empty);


        verify(cursoRepository, times(1)).saveAll(empty);
    }
}
