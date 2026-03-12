package com.example.desafio.services.impl;

import com.example.desafio.entities.Disciplina;
import com.example.desafio.repositories.DisciplinaRepository;
import com.example.desafio.services.DisciplinaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Service
@AllArgsConstructor
public class DisciplinaServiceImpl implements DisciplinaService {

    private DisciplinaRepository disciplinaRepository;

    @Transactional
    public void salvarDisciplinas(Collection<Disciplina> disciplina){
        disciplinaRepository.saveAll(disciplina);
    }
}
