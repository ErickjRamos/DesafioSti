package com.example.desafio.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_aluno_disciplina")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDisciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer notaAluno;
    private String anoSemestre;
    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
}
