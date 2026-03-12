package com.example.desafio.repositories;
import com.example.desafio.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByCodigoCurso(String codigoCurso);

    @Modifying
    @Query("""
            UPDATE Curso c
            SET c.MediaCr = (
                SELECT ROUND(AVG(DISTINCT a.cr))
                FROM AlunoDisciplina ad
                JOIN ad.aluno a
                WHERE ad.disciplina.curso = c
            )
        """)
    void calcularCrCursos();

}
