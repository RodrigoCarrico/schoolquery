package br.com.onboard.schoolquery.disciplina.api;

import br.com.onboard.schoolquery.disciplina.DisciplinaFactory;
import br.com.onboard.schoolquery.disciplina.model.Disciplina;
import br.com.onboard.schoolquery.disciplina.repository.DisciplinaRepository;
import br.com.onboard.schoolquery.pessoa.professor.ProfessorFactory;
import br.com.onboard.schoolquery.pessoa.professor.model.Professor;
import br.com.onboard.schoolquery.pessoa.professor.repository.ProfessorRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DisciplinaControllerTest {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private MockMvc mockMvc;

    private Disciplina disciplina1;
    private Professor professor1;

    @BeforeAll
    void setup() {
        professor1 = ProfessorFactory.buildProfessor1();
        ProfessorFactory.salvaBD(professor1, professorRepository);

        disciplina1 = DisciplinaFactory.buildDisciplina1(professor1);
        DisciplinaFactory.salvaBD(disciplina1, disciplinaRepository);
    }

    @Test
    @DisplayName("Teste de Pesquisa de Disciplina pela Sigla")
    void deveRetornarDisciplinaPelaSigla() throws Exception {
        mockMvc.perform(
                get(DisciplinaController.PATH + "/find").param("sigla", disciplina1.getSigla()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.content[0].sigla", is(disciplina1.getSigla())));
    }

    @Test
    @DisplayName("Teste de Pesquisa de Disciplina pelo Professor")
    void deveRetornarDisciplinaPeloProfessor() throws Exception {
        mockMvc.perform(
                get(DisciplinaController.PATH + "/find").param("professorId", disciplina1.getProfessor().getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.content[0].professor.id", is(disciplina1.getProfessor().getId())));
    }

    @Test
    @DisplayName("Teste de Pesquisa todas Disciplinas")
    void deveRetornarTodasAsDisciplina() throws Exception {
        mockMvc.perform(
                get(DisciplinaController.PATH + "/find"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.totalElements", Matchers.greaterThan(0)));
    }


}