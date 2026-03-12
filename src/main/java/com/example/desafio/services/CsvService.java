package com.example.desafio.services;

import com.example.desafio.dto.AtributosNotaDTO;

import java.util.List;

public interface CsvService {

    void persistirCsv(List<AtributosNotaDTO> dados);

    List<AtributosNotaDTO> lerCsv(String caminho) throws Exception;
}
