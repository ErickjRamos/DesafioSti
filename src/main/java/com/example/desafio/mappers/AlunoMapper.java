package com.example.desafio.mappers;

import com.example.desafio.dto.ResponseAlunoDto;
import com.example.desafio.entities.Aluno;

public class AlunoMapper {


    public static ResponseAlunoDto converterParaDto(Aluno aluno){
        ResponseAlunoDto responseAlunoDto = new ResponseAlunoDto();
        responseAlunoDto.setId(aluno.getId());
        responseAlunoDto.setMatricula(aluno.getMatricula());
        responseAlunoDto.setCr(aluno.getCr());
        return responseAlunoDto;
    }




}
