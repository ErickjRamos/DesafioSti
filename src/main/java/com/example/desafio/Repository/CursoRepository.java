package com.example.desafio.Repository;

import com.example.desafio.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByCodigoCurso(String codigoCurso);

}
