package com.example.desafio.service;
import com.example.desafio.Repository.AlunoDisciplinaRepository;
import com.example.desafio.Repository.CursoRepository;
import com.example.desafio.entities.Aluno;
import com.example.desafio.entities.AlunoDisciplina;
import com.example.desafio.entities.Curso;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CalculoCrCursosService {

    private final AlunoDisciplinaRepository alunoDisciplinaRepository;
    private final CursoRepository cursoRepository;

    @Transactional
    public void calcularESalvarCrCursos() {

        List<AlunoDisciplina> registros = alunoDisciplinaRepository.findAll();

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