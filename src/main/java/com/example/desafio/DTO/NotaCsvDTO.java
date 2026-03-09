package com.example.desafio.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class NotaCsvDTO {

    private String matricula;
    private String codigoDisciplina;
    private String codigoCurso;
    private Integer nota;
    private Integer cargaHoraria;
    private String anoSemestre;

    public NotaCsvDTO(String matricula, String codigoDisciplina, String codigoCurso, Integer nota, Integer cargaHoraria, String anoSemestre) {
        this.matricula = matricula;
        this.codigoDisciplina = codigoDisciplina;
        this.codigoCurso = codigoCurso;
        this.nota = nota;
        this.cargaHoraria = cargaHoraria;
        this.anoSemestre = anoSemestre;
    }
}
