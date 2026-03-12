package com.example.desafio.controllers;

import com.example.desafio.dto.ResponseAlunoDTO;
import com.example.desafio.services.AlunoService;
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

@WebMvcTest(AlunoController.class)
class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AlunoService alunoService;

    @Test
    void shouldReturnOkWithListOfAlunos() throws Exception {

        ResponseAlunoDTO dto1 = new ResponseAlunoDTO();
        ResponseAlunoDTO dto2 = new ResponseAlunoDTO();
        List<ResponseAlunoDTO> alunos = Arrays.asList(dto1, dto2);

        when(alunoService.buscarAlunos()).thenReturn(alunos);


        mockMvc.perform(get("/api/alunos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(alunoService, times(1)).buscarAlunos();
    }

    @Test
    void shouldReturnOkWithEmptyListWhenNoAlunosExist() throws Exception {

        when(alunoService.buscarAlunos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/alunos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void shouldReturn500WhenServiceThrowsException() throws Exception {

        when(alunoService.buscarAlunos()).thenThrow(new RuntimeException("Unexpected error"));


        mockMvc.perform(get("/api/alunos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldCallBuscarAlunosOnce() throws Exception {

        when(alunoService.buscarAlunos()).thenReturn(Collections.emptyList());


        mockMvc.perform(get("/api/alunos"));


        verify(alunoService, times(1)).buscarAlunos();
        verifyNoMoreInteractions(alunoService);
    }
}
