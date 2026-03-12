package com.example.desafio.repositories;

import com.example.desafio.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByMatricula(Long matricula);

    @Modifying
    @Query("""
                UPDATE Aluno a
                SET a.cr = (
                    SELECT ROUND(SUM(ad.notaAluno * d.cargaHoraria) / SUM(d.cargaHoraria))
                    FROM AlunoDisciplina ad
                    JOIN ad.disciplina d
                    WHERE ad.aluno = a
                )
                WHERE EXISTS (
                    SELECT 1 FROM AlunoDisciplina ad WHERE ad.aluno = a
                )
            """)
    void calcularCrAlunos();
}