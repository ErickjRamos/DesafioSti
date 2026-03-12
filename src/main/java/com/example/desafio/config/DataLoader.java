package com.example.desafio.config;

import com.example.desafio.dto.AtributosNotaDTO;
import com.example.desafio.dto.ResponseAlunoDTO;
import com.example.desafio.dto.ResponseCursoDTO;
import com.example.desafio.entities.Aluno;
import com.example.desafio.entities.Curso;
import com.example.desafio.services.AlunoService;
import com.example.desafio.services.CursoService;
import com.example.desafio.services.LerCsvService;
import com.example.desafio.services.PersistirCsvService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final LerCsvService lerCsvService;
    private final PersistirCsvService persistirCsvService;
    private final AlunoService alunoService;
    private final CursoService cursoService;

    @Override
    public void run(String... args) throws Exception {
        List<Aluno> alunosCalculados;
        List<Curso> cursosCalculados;
        List<AtributosNotaDTO> dados = lerCsvService.lerCsv("src/main/resources/notas.csv");

        persistirCsvService.importar(dados);

        alunoService.calcularCrAlunos();
        List<ResponseAlunoDTO> alunos = alunoService.exibirAlunos();

        cursoService.calcularCrCursos();
        List<ResponseCursoDTO> cursos = cursoService.exibirCursos();

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
