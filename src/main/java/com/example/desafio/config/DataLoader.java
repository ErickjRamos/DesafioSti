package com.example.desafio.config;
import com.example.desafio.DTO.NotaCsvDTO;
import com.example.desafio.entities.Aluno;
import com.example.desafio.services.CalcularCrAlunosService;
import com.example.desafio.services.CalcularCrCursosService;
import com.example.desafio.services.LerCsvService;
import com.example.desafio.services.PersistirCsvService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final LerCsvService lerCsvService;
    private final PersistirCsvService persistirCsvService;
    private final CalcularCrAlunosService calcularCrAlunosService;
    private final CalcularCrCursosService calcularCrCursosService;

@Override
public void run(String... args) throws Exception {
    List<Aluno> alunosCalculados = new ArrayList<>();
    List<NotaCsvDTO> dados = lerCsvService.lerCsv("src/main/resources/notas.csv");

    //System.out.println("Linhas lidas: " + dados.size());

    persistirCsvService.importar(dados);
    alunosCalculados = calcularCrAlunosService.calcularESalvarCrAlunos();
    calcularCrCursosService.calcularESalvarCrCursos();

    System.out.println("------- O CR dos alunos é: -------");
    for(Aluno aluno: alunosCalculados) {
        System.out.println(aluno.getMatricula() + " - " + aluno.getCr());
    }
}
}
