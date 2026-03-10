package com.example.desafio.controllers;
import com.example.desafio.services.CalcularCrAlunosService;
import com.example.desafio.services.CalcularCrCursosService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculo")
@AllArgsConstructor
public class CalculoController {
    private final CalcularCrAlunosService calcularCrAlunosService;
    private final CalcularCrCursosService calcularCrCursosService;

    @GetMapping("/cr-alunos")
    public ResponseEntity<Void> calcularCrAlunos() {
        calcularCrAlunosService.calcularESalvarCrAlunos();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cr-cursos")
    public ResponseEntity<Void> calcularCrCursos() {
        calcularCrCursosService.calcularESalvarCrCursos();
        return ResponseEntity.ok().build();
    }
}
