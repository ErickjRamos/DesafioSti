package com.example.desafio.services.impl;

import com.example.desafio.entities.AlunoDisciplina;
import com.example.desafio.repositories.AlunoDisciplinaRepository;
import com.example.desafio.services.AlunoDisciplinaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class AlunoDisciplinaServiceImpl implements AlunoDisciplinaService {
    private final AlunoDisciplinaRepository alunoDisciplinaRepository;

    public void salvarAlunoDisciplinas(List<AlunoDisciplina> alunoDisciplina) {
        alunoDisciplinaRepository.saveAll(alunoDisciplina);
    }
}
