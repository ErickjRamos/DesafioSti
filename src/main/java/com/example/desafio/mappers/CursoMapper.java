package com.example.desafio.mappers;

import com.example.desafio.dto.ResponseCursoDTO;
import com.example.desafio.entities.Curso;

public class CursoMapper {


    public static ResponseCursoDTO converterParaDto(Curso curso) {
        ResponseCursoDTO responseCursoDto = new ResponseCursoDTO();
        responseCursoDto.setId(curso.getId());
        responseCursoDto.setCodigoCurso(curso.getCodigoCurso());
        responseCursoDto.setMediaCr(curso.getMediaCr());
        return responseCursoDto;
    }
}
