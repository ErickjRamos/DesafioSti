package com.example.desafio.repositories;
import com.example.desafio.entities.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    Optional<Disciplina> findByCodigoDisciplina(String codigoDisciplina);

}
