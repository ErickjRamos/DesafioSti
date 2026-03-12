package com.example.desafio.services.impl;

import com.example.desafio.dto.AtributosNotaDTO;
import com.example.desafio.entities.Aluno;
import com.example.desafio.entities.AlunoDisciplina;
import com.example.desafio.entities.Curso;
import com.example.desafio.entities.Disciplina;
import com.example.desafio.services.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CsvServiceImplTest {

    @Mock
    private AlunoService alunoService;

    @Mock
    private DisciplinaService disciplinaService;

    @Mock
    private CursoService cursoService;

    @Mock
    private AlunoDisciplinaService alunoDisciplinaService;

    @InjectMocks
    private CsvServiceImpl csvService;

    @Test
    void shouldPersistAllEntitiesFromDTOList() {

        AtributosNotaDTO dto = new AtributosNotaDTO("MAT001", "DISC001", "CURSO001", 60, 8, "2024.1");
        List<AtributosNotaDTO> dados = Collections.singletonList(dto);


        csvService.persistirCsv(dados);

        verify(alunoService, times(1)).salvarAlunos(any());
        verify(disciplinaService, times(1)).salvarDisciplinas(any());
        verify(cursoService, times(1)).salvarCursos(any());
        verify(alunoDisciplinaService, times(1)).salvarAlunoDisciplinas(any());
    }

    @Test
    void shouldDeduplicateAlunosWithSameMatricula() {

        AtributosNotaDTO dto1 = new AtributosNotaDTO("MAT001", "DISC001", "CURSO001", 60, 8, "2024.1");
        AtributosNotaDTO dto2 = new AtributosNotaDTO("MAT001", "DISC002", "CURSO001", 60, 9, "2024.1");


        csvService.persistirCsv(Arrays.asList(dto1, dto2));

        ArgumentCaptor<Collection<Aluno>> captor = ArgumentCaptor.forClass(Collection.class);
        verify(alunoService).salvarAlunos(captor.capture());
        assertEquals(1, captor.getValue().size());
    }

    @Test
    void shouldDeduplicateCursosWithSameCodigo() {

        AtributosNotaDTO dto1 = new AtributosNotaDTO("MAT001", "DISC001", "CURSO001", 60, 8, "2024.1");
        AtributosNotaDTO dto2 = new AtributosNotaDTO("MAT002", "DISC002", "CURSO001", 60, 7, "2024.1");


        csvService.persistirCsv(Arrays.asList(dto1, dto2));


        ArgumentCaptor<Collection<Curso>> captor = ArgumentCaptor.forClass(Collection.class);
        verify(cursoService).salvarCursos(captor.capture());
        assertEquals(1, captor.getValue().size());
    }

    @Test
    void shouldDeduplicateDisciplinasWithSameCodigo() {

        AtributosNotaDTO dto1 = new AtributosNotaDTO("MAT001", "DISC001", "CURSO001", 60, 8, "2024.1");
        AtributosNotaDTO dto2 = new AtributosNotaDTO("MAT002", "DISC001", "CURSO001", 60, 7, "2024.1");


        csvService.persistirCsv(Arrays.asList(dto1, dto2));


        ArgumentCaptor<Collection<Disciplina>> captor = ArgumentCaptor.forClass(Collection.class);
        verify(disciplinaService).salvarDisciplinas(captor.capture());
        assertEquals(1, captor.getValue().size());
    }

    @Test
    void shouldCreateOneAlunoDisciplinaPerRow() {

        AtributosNotaDTO dto1 = new AtributosNotaDTO("MAT001", "DISC001", "CURSO001", 60, 8, "2024.1");
        AtributosNotaDTO dto2 = new AtributosNotaDTO("MAT002", "DISC002", "CURSO002", 45, 7, "2024.2");


        csvService.persistirCsv(Arrays.asList(dto1, dto2));

        ArgumentCaptor<List<AlunoDisciplina>> captor = ArgumentCaptor.forClass(List.class);
        verify(alunoDisciplinaService).salvarAlunoDisciplinas(captor.capture());
        assertEquals(2, captor.getValue().size());
    }

    @Test
    void shouldPersistEmptyListWithoutCallingRepositories() {
        csvService.persistirCsv(Collections.emptyList());


        verify(alunoService, times(1)).salvarAlunos(argThat(Collection::isEmpty));
        verify(disciplinaService, times(1)).salvarDisciplinas(argThat(Collection::isEmpty));
        verify(cursoService, times(1)).salvarCursos(argThat(Collection::isEmpty));
        verify(alunoDisciplinaService, times(1)).salvarAlunoDisciplinas(argThat(List::isEmpty));
    }


    @Test
    void shouldParseValidCsvFile(@TempDir Path tempDir) throws Exception {

        File csv = tempDir.resolve("notas.csv").toFile();
        try (FileWriter fw = new FileWriter(csv)) {
            fw.write("matricula,codigoDisciplina,codigoCurso,cargaHoraria,nota,anoSemestre\n");
            fw.write("MAT001,DISC001,CURSO001,60,8,2024.1\n");
            fw.write("MAT002,DISC002,CURSO002,45,9,2024.2\n");
        }


        List<AtributosNotaDTO> result = csvService.lerCsv(csv.getAbsolutePath());


        assertNotNull(result);
        assertEquals(2, result.size());

        AtributosNotaDTO first = result.get(0);
        assertEquals("MAT001", first.getMatricula());
        assertEquals("DISC001", first.getCodigoDisciplina());
        assertEquals("CURSO001", first.getCodigoCurso());
        assertEquals(60, first.getCargaHoraria());
        assertEquals(8, first.getNota());
        assertEquals("2024.1", first.getAnoSemestre());
    }

    @Test
    void shouldReturnEmptyListForCsvWithOnlyHeader(@TempDir Path tempDir) throws Exception {

        File csv = tempDir.resolve("empty.csv").toFile();
        try (FileWriter fw = new FileWriter(csv)) {
            fw.write("matricula,codigoDisciplina,codigoCurso,cargaHoraria,nota,anoSemestre\n");
        }


        List<AtributosNotaDTO> result = csvService.lerCsv(csv.getAbsolutePath());


        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldThrowExceptionForNonExistentFile() {
        assertThrows(Exception.class, () -> csvService.lerCsv("/path/that/does/not/exist.csv"));
    }
}
