package com.example.desafio.Repository;

import com.example.desafio.entities.Aluno;
import com.example.desafio.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByMatricula(Long matricula);
}
