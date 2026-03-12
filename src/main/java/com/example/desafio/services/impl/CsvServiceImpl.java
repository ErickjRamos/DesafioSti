package com.example.desafio.services.impl;

import com.example.desafio.dto.AtributosNotaDTO;
import com.example.desafio.entities.Aluno;
import com.example.desafio.entities.AlunoDisciplina;
import com.example.desafio.entities.Curso;
import com.example.desafio.entities.Disciplina;
import com.example.desafio.services.*;
import com.opencsv.CSVReader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@AllArgsConstructor
public class CsvServiceImpl implements CsvService {

    private final AlunoService alunoService;
    private final DisciplinaService disciplinaService;
    private final CursoService cursoService;
    private final AlunoDisciplinaService alunoDisciplinaService;

    @Transactional
    public void persistirCsv(List<AtributosNotaDTO> dados) {

        Map<String, Curso> cursos = new HashMap<>();
        Map<String, Disciplina> disciplinas = new HashMap<>();
        Map<String, Aluno> alunos = new HashMap<>();

        List<AlunoDisciplina> historicos = new ArrayList<>();

        for (AtributosNotaDTO dto : dados) {

            Curso curso = cursos.computeIfAbsent(dto.getCodigoCurso(), codigo -> {
                Curso c = new Curso();
                c.setCodigoCurso(codigo);
                return c;
            });

            Disciplina disciplina = disciplinas.computeIfAbsent(dto.getCodigoDisciplina(), codigo -> {
                Disciplina d = new Disciplina();
                d.setCodigoDisciplina(codigo);
                d.setCargaHoraria(dto.getCargaHoraria());
                d.setCurso(curso);
                return d;
            });

            Aluno aluno = alunos.computeIfAbsent(dto.getMatricula(), matricula -> {
                Aluno a = new Aluno();
                a.setMatricula(matricula);
                return a;
            });

            AlunoDisciplina ad = new AlunoDisciplina();

            ad.setAluno(aluno);
            ad.setDisciplina(disciplina);
            ad.setNotaAluno(dto.getNota());
            ad.setAnoSemestre(dto.getAnoSemestre());

            historicos.add(ad);
        }
        alunoService.salvarAlunos(alunos.values());
        disciplinaService.salvarDisciplinas(disciplinas.values());
        cursoService.salvarCursos(cursos.values());
        alunoDisciplinaService.salvarAlunoDisciplinas(historicos);
    }

    public List<AtributosNotaDTO> lerCsv(String caminho) throws Exception {

        List<AtributosNotaDTO> lista = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(caminho))) {

            List<String[]> linhas = reader.readAll();

            for (int i = 1; i < linhas.size(); i++) {

                String[] l = linhas.get(i);

                AtributosNotaDTO dto = new AtributosNotaDTO(l[0], l[1], l[2], Integer.parseInt(l[3]), Integer.parseInt(l[4]), l[5]);

                lista.add(dto);
            }
        }

        return lista;
    }
}
