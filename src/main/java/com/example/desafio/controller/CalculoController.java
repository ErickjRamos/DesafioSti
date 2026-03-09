package com.example.desafio.controller;
import com.example.desafio.service.CalculoCrAlunosService;
import com.example.desafio.service.CalculoCrCursosService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculo")
@AllArgsConstructor
public class CalculoController {
    private final CalculoCrAlunosService calculoCrAlunosService;
    private final CalculoCrCursosService calculoCrCursosService;

    @GetMapping("/cr-alunos")
    public ResponseEntity<Void> calcularCrAlunos() {
        calculoCrAlunosService.calcularESalvarCrAlunos();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cr-cursos")
    public ResponseEntity<Void> calcularCrCursos() {
        calculoCrCursosService.calcularESalvarCrCursos();
        return ResponseEntity.ok().build();
    }
}
