package com.example.desafio.config;
import com.example.desafio.DTO.NotaCsvDTO;
import com.example.desafio.service.CsvService;
import com.example.desafio.service.ImportacaoService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CsvService csvService;
    private final ImportacaoService importacaoService;

@Override
public void run(String... args) throws Exception {

    List<NotaCsvDTO> dados = csvService.lerCsv("src/main/resources/notas.csv");

    System.out.println("Linhas lidas: " + dados.size());

    importacaoService.importar(dados);
}
}
