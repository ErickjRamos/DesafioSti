package com.example.desafio.controllers;

import com.example.desafio.dto.ResponseCursoDTO;
import com.example.desafio.services.CursoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CursoController {
    private final CursoService cursoService;

    @GetMapping("/cursos")
    public ResponseEntity<List<ResponseCursoDTO>> buscarCursos() {
        return ResponseEntity.ok(cursoService.buscarCursos());
    }
}
