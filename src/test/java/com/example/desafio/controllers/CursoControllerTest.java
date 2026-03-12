package com.example.desafio.controllers;

import com.example.desafio.dto.ResponseCursoDTO;
import com.example.desafio.services.CursoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CursoController.class)
class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CursoService cursoService;

    @Test
    void shouldReturnOkWithListOfCursos() throws Exception {

        ResponseCursoDTO dto1 = new ResponseCursoDTO();
        ResponseCursoDTO dto2 = new ResponseCursoDTO();
        List<ResponseCursoDTO> cursos = Arrays.asList(dto1, dto2);

        when(cursoService.buscarCursos()).thenReturn(cursos);


        mockMvc.perform(get("/api/cursos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(cursoService, times(1)).buscarCursos();
    }

    @Test
    void shouldReturnOkWithEmptyListWhenNoCursosExist() throws Exception {

        when(cursoService.buscarCursos()).thenReturn(Collections.emptyList());


        mockMvc.perform(get("/api/cursos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void shouldReturn500WhenServiceThrowsException() throws Exception {

        when(cursoService.buscarCursos()).thenThrow(new RuntimeException("Unexpected error"));


        mockMvc.perform(get("/api/cursos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldCallBuscarCursosOnce() throws Exception {

        when(cursoService.buscarCursos()).thenReturn(Collections.emptyList());


        mockMvc.perform(get("/api/cursos"));


        verify(cursoService, times(1)).buscarCursos();
        verifyNoMoreInteractions(cursoService);
    }
}
