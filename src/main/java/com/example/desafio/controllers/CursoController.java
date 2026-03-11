package com.example.desafio.controllers;
import com.example.desafio.services.CursoService;
import com.example.desafio.services.impl.CursoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CursoController {
    private final CursoService cursoService;

    @GetMapping("/cursos")
    public ResponseEntity<Void> calcularCrCursos() {
        cursoService.calcularCrCursos();
        return ResponseEntity.ok().build();
    }
}
