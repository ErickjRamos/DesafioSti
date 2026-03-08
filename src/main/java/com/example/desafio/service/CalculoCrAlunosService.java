package com.example.desafio.service;

import com.example.desafio.Repository.AlunoDisciplinaRepository;
import com.example.desafio.Repository.AlunoRepository;
import com.example.desafio.entities.Aluno;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculoCrAlunosService {

    private final AlunoDisciplinaRepository alunoDisciplinaRepository;
    private final AlunoRepository alunoRepository;

    public CalculoCrAlunosService(AlunoDisciplinaRepository alunoDisciplinaRepository,
                                  AlunoRepository alunoRepository) {
        this.alunoDisciplinaRepository = alunoDisciplinaRepository;
        this.alunoRepository = alunoRepository;
    }

    public void calcularESalvarCrAlunos() {

        var registros = alunoDisciplinaRepository.findAll();
        registros.stream()
                .collect(Collectors.groupingBy(ad -> ad.getAluno()))
                .forEach((aluno, lista) -> {

                    double somaNotasPeso = lista.stream()
                            .mapToDouble(ad ->
                                    ad.getNotaAluno() *
                                            ad.getDisciplina().getCargaHoraria())
                            .sum();

                    double somaCarga = lista.stream()
                            .mapToInt(ad ->
                                    ad.getDisciplina().getCargaHoraria())
                            .sum();

                    int cr = (int) Math.round(somaNotasPeso / somaCarga);

                    cr = Math.max(0, Math.min(100, cr));

                    aluno.setCr(cr);

                    alunoRepository.save(aluno);
                });
    }
}
