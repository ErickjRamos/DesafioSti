package com.example.desafio.controller;

import com.example.desafio.entities.Aluno;
import com.example.desafio.entities.AlunoDisciplina;
import com.example.desafio.service.CalculoCrAlunosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/calculo")
public class CalculoController {
    private final CalculoCrAlunosService calculoCrAlunosService;

    public CalculoController(CalculoCrAlunosService calculoCrAlunosService){
        this.calculoCrAlunosService = calculoCrAlunosService;
    }


    @GetMapping("/cr-alunos")
    public ResponseEntity<Void> calcularCrAlunos() {
        calculoCrAlunosService.calcularESalvarCrAlunos();
        return ResponseEntity.ok().build();
    }
}
