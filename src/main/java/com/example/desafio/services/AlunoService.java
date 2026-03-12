package com.example.desafio.services;

import com.example.desafio.dto.ResponseAlunoDTO;
import com.example.desafio.entities.Aluno;

import java.util.Collection;
import java.util.List;

public interface AlunoService {

    void calcularCrAlunos();


    List<ResponseAlunoDTO> buscarAlunos();

    void salvarAlunos(Collection<Aluno> alunos);
}
