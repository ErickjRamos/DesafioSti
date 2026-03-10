package com.example.desafio.config;
import com.example.desafio.DTO.NotaCsvDTO;
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

@Override
public void run(String... args) throws Exception {

    List<NotaCsvDTO> dados = lerCsvService.lerCsv("src/main/resources/notas.csv");

    System.out.println("Linhas lidas: " + dados.size());

    persistirCsvService.importar(dados);
}
}
