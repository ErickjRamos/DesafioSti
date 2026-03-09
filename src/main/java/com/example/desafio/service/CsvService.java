package com.example.desafio.service;
import com.example.desafio.DTO.NotaCsvDTO;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    public List<NotaCsvDTO> lerCsv(String caminho) throws Exception {

        List<NotaCsvDTO> lista = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(caminho))) {

            List<String[]> linhas = reader.readAll();//*****Horroroso pra arquivos grandes*****

            for (int i = 1; i < linhas.size(); i++) {

                String[] l = linhas.get(i);

                NotaCsvDTO dto = new NotaCsvDTO(l[0] , l[1], l[2], Integer.parseInt(l[3]), Integer.parseInt(l[4]), l[5]);

                lista.add(dto);
            }
        }

        return lista;
    }
}
