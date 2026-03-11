package com.example.desafio.services;
import com.example.desafio.dto.AtributosNotaDTO;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class LerCsvService {

    public List<AtributosNotaDTO> lerCsv(String caminho) throws Exception {

        List<AtributosNotaDTO> lista = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(caminho))) {

            List<String[]> linhas = reader.readAll();//*****Horroroso pra arquivos grandes*****

            for (int i = 1; i < linhas.size(); i++) {

                String[] l = linhas.get(i);

                AtributosNotaDTO dto = new AtributosNotaDTO(l[0] , l[1], l[2], Integer.parseInt(l[3]), Integer.parseInt(l[4]), l[5]);

                lista.add(dto);
            }
        }

        return lista;
    }
}
