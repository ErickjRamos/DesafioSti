package com.example.desafio.services;

import com.example.desafio.dto.AtributosNotaDTO;

import java.util.List;

public interface LerCsvService {

    List<AtributosNotaDTO> lerCsv(String caminho) throws Exception;
}
