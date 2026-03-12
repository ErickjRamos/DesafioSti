package com.example.desafio.mappers;

import com.example.desafio.dto.ResponseAlunoDTO;
import com.example.desafio.entities.Aluno;

public class AlunoMapper {


    public static ResponseAlunoDTO converterParaDto(Aluno aluno) {
        ResponseAlunoDTO responseAlunoDto = new ResponseAlunoDTO();
        responseAlunoDto.setId(aluno.getId());
        responseAlunoDto.setMatricula(aluno.getMatricula());
        responseAlunoDto.setCr(aluno.getCr());
        return responseAlunoDto;
    }


}
