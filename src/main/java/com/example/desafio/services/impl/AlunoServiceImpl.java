package com.example.desafio.services.impl;

import com.example.desafio.dto.ResponseAlunoDTO;
import com.example.desafio.entities.Aluno;
import com.example.desafio.mappers.AlunoMapper;
import com.example.desafio.repositories.AlunoDisciplinaRepository;
import com.example.desafio.repositories.AlunoRepository;
import com.example.desafio.entities.AlunoDisciplina;
import com.example.desafio.services.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository alunoRepository;

    /*
        public List<Aluno> calcularCrAlunos() {
            return alunoRepository.findAllWithCalculatedCr()
                    .stream()
                    .map(row -> {
                        Aluno aluno = (Aluno) row[0];
                        aluno.setCr(((Number) row[1]).intValue());
                        return aluno;
                    })
                    .toList();
        }

        @Transactional
        public void salvarCrAlunos() {
            alunoRepository.updateCrForAllAlunos();
        }

        @Transactional
        public List<Aluno> calcularESalvarCrAlunos() {
            salvarCrAlunos();
            return calcularCrAlunos();
        }
    */
    @Transactional
    public List<Aluno> calcularCrAlunos() {
        alunoRepository.calcularCrAlunos();
        List<Aluno> alunos = alunoRepository.findAll();
        return alunos;
    }
}

