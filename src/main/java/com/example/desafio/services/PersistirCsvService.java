package com.example.desafio.services;
import com.example.desafio.DTO.NotaCsvDTO;
import com.example.desafio.repositories.AlunoDisciplinaRepository;
import com.example.desafio.repositories.AlunoRepository;
import com.example.desafio.repositories.CursoRepository;
import com.example.desafio.repositories.DisciplinaRepository;
import com.example.desafio.entities.Aluno;
import com.example.desafio.entities.AlunoDisciplina;
import com.example.desafio.entities.Curso;
import com.example.desafio.entities.Disciplina;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PersistirCsvService {

    private final CursoRepository cursoRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final AlunoRepository alunoRepository;
    private final AlunoDisciplinaRepository alunoDisciplinaRepository;

    @Transactional
    public void importar(List<NotaCsvDTO> dados) {

        Map<String, Curso> cursos = new HashMap<>();
        Map<String, Disciplina> disciplinas = new HashMap<>();
        Map<String, Aluno> alunos = new HashMap<>();

        List<AlunoDisciplina> historicos = new ArrayList<>();

        for (NotaCsvDTO dto : dados) {

            Curso curso = cursos.computeIfAbsent(dto.getCodigoCurso(), codigo -> {
                Curso c = new Curso();
                c.setCodigoCurso(codigo);
                return cursoRepository.save(c);
            });

            Disciplina disciplina = disciplinas.computeIfAbsent(dto.getCodigoDisciplina(), codigo -> {
                Disciplina d = new Disciplina();
                d.setCodigoDisciplina(codigo);
                d.setCargaHoraria(dto.getCargaHoraria());
                d.setCurso(curso);
                return disciplinaRepository.save(d);
            });

            Aluno aluno = alunos.computeIfAbsent(dto.getMatricula(), matricula-> {
                Aluno a = new Aluno();
                a.setMatricula(matricula);
                return alunoRepository.save(a);
            });

            AlunoDisciplina ad = new AlunoDisciplina();

            ad.setAluno(aluno);
            ad.setDisciplina(disciplina);
            ad.setNotaAluno(dto.getNota());
            ad.setAnoSemestre(dto.getAnoSemestre());

            historicos.add(ad);
        }

        alunoDisciplinaRepository.saveAll(historicos);
    }
}
