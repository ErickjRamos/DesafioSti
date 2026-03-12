package com.example.desafio.services.impl;

import com.example.desafio.dto.ResponseAlunoDTO;
import com.example.desafio.dto.ResponseCursoDTO;
import com.example.desafio.mappers.AlunoMapper;
import com.example.desafio.mappers.CursoMapper;
import com.example.desafio.repositories.AlunoDisciplinaRepository;
import com.example.desafio.repositories.CursoRepository;
import com.example.desafio.entities.Aluno;
import com.example.desafio.entities.AlunoDisciplina;
import com.example.desafio.entities.Curso;
import com.example.desafio.services.CursoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    @Transactional
    public void calcularCrCursos() {
        cursoRepository.calcularCrCursos();
    }

    @Transactional(readOnly = true)
    public List<ResponseCursoDTO> buscarCursos() {
        return cursoRepository.findAll()
                .stream()
                .map(curso -> CursoMapper.converterParaDto(curso))
                .collect(Collectors.toList());
    }

    @Transactional
    public void salvarCursos(Collection<Curso> cursos) {
        cursoRepository.saveAll(cursos);
    }
}