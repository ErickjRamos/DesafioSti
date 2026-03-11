package com.example.desafio.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AtributosNotaDTO {

    private String matricula;
    private String codigoDisciplina;
    private String codigoCurso;
    private Integer nota;
    private Integer cargaHoraria;
    private String anoSemestre;

}
