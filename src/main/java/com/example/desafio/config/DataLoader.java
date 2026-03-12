package com.example.desafio.config;

import com.example.desafio.dto.AtributosNotaDTO;
import com.example.desafio.dto.ResponseAlunoDTO;
import com.example.desafio.dto.ResponseCursoDTO;
import com.example.desafio.entities.Aluno;
import com.example.desafio.entities.Curso;
import com.example.desafio.services.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final CsvService csvService;
   // private final LerCsvService lerCsvService;
   //private final PersistirCsvService persistirCsvService;
    private final AlunoService alunoService;
    private final CursoService cursoService;

    @Override
    public void run(String... args) throws Exception {
        List<Aluno> alunosCalculados;
        List<Curso> cursosCalculados;
        List<AtributosNotaDTO> dados = csvService.lerCsv("src/main/resources/notas.csv");

        csvService.persistirCsv(dados);

        alunoService.calcularCrAlunos();
        List<ResponseAlunoDTO> alunos = alunoService.buscarAlunos();//buscar alunos

        cursoService.calcularCrCursos();
        List<ResponseCursoDTO> cursos = cursoService.buscarCursos();//buscar alunos

        System.out.println("------- O CR dos alunos é: -------");
        for (ResponseAlunoDTO aluno : alunos) {
            System.out.println(aluno.getMatricula() + " - " + aluno.getCr());
        }

        System.out.println("----- Média de CR dos cursos ------");
        for (ResponseCursoDTO curso : cursos) {
            System.out.println(curso.getCodigoCurso() + " - " +
                    curso.getMediaCr());
        }
    }
}
