package com.example.desafio.service;

import com.example.desafio.Repository.AlunoDisciplinaRepository;

import com.example.desafio.Repository.CursoRepository;
import com.example.desafio.entities.Aluno;
import com.example.desafio.entities.AlunoDisciplina;
import com.example.desafio.entities.Curso;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CalculoCrCursosService {

    private final AlunoDisciplinaRepository alunoDisciplinaRepository;
    private final CursoRepository cursoRepository;

    public CalculoCrCursosService(AlunoDisciplinaRepository alunoDisciplinaRepository,
                                  CursoRepository cursoRepository) {
        this.alunoDisciplinaRepository = alunoDisciplinaRepository;
        this.cursoRepository = cursoRepository;
    }

    public void calcularESalvarCrCursos() {

        var registros = alunoDisciplinaRepository.findAll();

        // Map<Curso, Set<Aluno>>
        Map<Curso, Set<Aluno>> alunosPorCurso =
                registros.stream()
                        .collect(Collectors.groupingBy(
                                ad -> ad.getDisciplina().getCurso(),
                                Collectors.mapping(
                                        AlunoDisciplina::getAluno,
                                        Collectors.toSet()
                                )
                        ));

        alunosPorCurso.forEach((curso, alunos) -> {

            double media = alunos.stream()
                    .mapToInt(Aluno::getCr)
                    .average()
                    .orElse(0);

            int crFinal = (int) Math.round(media);

            curso.setMediaCr(crFinal);

            cursoRepository.save(curso);
        });
    }
}