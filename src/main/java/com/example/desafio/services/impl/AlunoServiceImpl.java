package com.example.desafio.services.impl;

import com.example.desafio.dto.ResponseAlunoDTO;
import com.example.desafio.dto.ResponseCursoDTO;
import com.example.desafio.entities.Aluno;
import com.example.desafio.mappers.AlunoMapper;
import com.example.desafio.repositories.AlunoDisciplinaRepository;
import com.example.desafio.repositories.AlunoRepository;
import com.example.desafio.entities.AlunoDisciplina;
import com.example.desafio.services.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository alunoRepository;

    @Transactional
    public void calcularCrAlunos() {
        alunoRepository.calcularCrAlunos();
    }

    @Transactional(readOnly = true)
    public List<ResponseAlunoDTO> exibirAlunos() {
        return alunoRepository.findAll()
                .stream()
                .map(aluno -> AlunoMapper.converterParaDto(aluno))
                .collect(Collectors.toList());
    }

    @Transactional
    public void salvarAlunos(Collection<Aluno> alunos) {
        alunoRepository.saveAll(alunos);
    }

}

