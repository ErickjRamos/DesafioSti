package com.example.desafio.Repository;

import com.example.desafio.entities.Aluno;
import com.example.desafio.entities.AlunoDisciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoDisciplinaRepository extends JpaRepository<AlunoDisciplina, Long> {

}
