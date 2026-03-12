package com.example.desafio.services;

import com.example.desafio.dto.ResponseCursoDTO;
import com.example.desafio.entities.Aluno;
import com.example.desafio.entities.Curso;

import java.util.Collection;
import java.util.List;

public interface CursoService {

    void calcularCrCursos();

    List<ResponseCursoDTO> buscarCursos();

    void salvarCursos(Collection<Curso> cursos);
}
