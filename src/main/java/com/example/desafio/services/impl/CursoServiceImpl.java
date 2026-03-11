package com.example.desafio.services.impl;

import com.example.desafio.repositories.AlunoDisciplinaRepository;
import com.example.desafio.repositories.CursoRepository;
import com.example.desafio.entities.Aluno;
import com.example.desafio.entities.AlunoDisciplina;
import com.example.desafio.entities.Curso;
import com.example.desafio.services.CursoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    @Transactional
    public List<Curso> calcularCrCursos() {
        cursoRepository.calcularCrCursos();
        List<Curso> Curso = cursoRepository.findAll();
        return Curso;
    }
}