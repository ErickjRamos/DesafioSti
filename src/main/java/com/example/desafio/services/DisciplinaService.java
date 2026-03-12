package com.example.desafio.services;

import com.example.desafio.entities.Aluno;
import com.example.desafio.entities.Disciplina;

import java.util.Collection;

public interface DisciplinaService {

    void salvarDisciplinas(Collection<Disciplina> disciplina);
}
