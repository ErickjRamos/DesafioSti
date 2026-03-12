package com.example.desafio.controllers;

import com.example.desafio.dto.ResponseAlunoDTO;
import com.example.desafio.services.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AlunoController {
    private final AlunoService alunoService;

    @GetMapping("/alunos")
    public ResponseEntity<List<ResponseAlunoDTO>> exibirAlunos() {
        return ResponseEntity.ok(alunoService.exibirAlunos());
    }


}