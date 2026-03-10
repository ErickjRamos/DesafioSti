package com.example.desafio.services;
import com.example.desafio.entities.Aluno;
import com.example.desafio.repositories.AlunoDisciplinaRepository;
import com.example.desafio.repositories.AlunoRepository;
import com.example.desafio.entities.AlunoDisciplina;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CalcularCrAlunosService {

    private final AlunoDisciplinaRepository alunoDisciplinaRepository;
    private final AlunoRepository alunoRepository;

    @Transactional
    public List<Aluno> calcularESalvarCrAlunos() {
        List<Aluno> alunosCalculados = new ArrayList<>();
        List<AlunoDisciplina> registros = alunoDisciplinaRepository.findAll();
        registros.stream()
                .collect(Collectors.groupingBy(ad -> ad.getAluno()))
                .forEach((aluno, lista) -> {

                    double somaNotasPeso = lista.stream()
                            .mapToDouble(ad ->
                                    ad.getNotaAluno() *
                                            ad.getDisciplina().getCargaHoraria())
                            .sum();

                    double somaCarga = lista.stream()
                            .mapToInt(ad ->
                                    ad.getDisciplina().getCargaHoraria())
                            .sum();

                    int cr = (int) Math.round(somaNotasPeso / somaCarga);

                    cr = Math.max(0, Math.min(100, cr));

                    aluno.setCr(cr);
                    alunosCalculados.add(aluno);
                    alunoRepository.save(aluno);
                });
        return alunosCalculados;
    }
}
