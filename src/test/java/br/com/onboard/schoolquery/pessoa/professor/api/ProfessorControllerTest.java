package br.com.onboard.schoolquery.pessoa.professor.api;

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
class ProfessorControllerTest {

    private ProfessorFactory professorFactoryTest;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private MockMvc mockMvc;

    private static Professor professor3 = ProfessorFactory.buildProfessor3();;

    @BeforeAll
    void setup() {
        ProfessorFactory.salvaBD(professor3, professorRepository);
    }

    @Test
    @DisplayName("Teste de Pesquisa de professores por nome")
    void getProfessoresByNome() throws Exception {
        mockMvc.perform(
                get(ProfessorController.PATH + "/find").param("nome", professor3.getNome()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.content[0].nome", is(professor3.getNome())));
    }

    @Test
    @DisplayName("Teste de Pesquisa de professores por email")
    void getProfessoresByEmail() throws Exception {
        mockMvc.perform(
                get(ProfessorController.PATH + "/find").param("email", professor3.getEmail()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.content[0].email", is(professor3.getEmail())));
    }

    @Test
    @DisplayName("Teste de Pesquisa de professores por Titulacao")
    void getProfessoresByTitulacao() throws Exception {
        mockMvc.perform(
                get(ProfessorController.PATH + "/find").param("titulacao", professor3.getTitulacao().name()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.totalElements", Matchers.greaterThan(0)))
                .andExpect(jsonPath("$.content[0].titulacao", is(professor3.getTitulacao().name())));
    }

    @Test
    @DisplayName("Teste de Pesquisa de professores por cpf")
    void getProfessoresByCpf() throws Exception {
        mockMvc.perform(
                get(ProfessorController.PATH + "/find").param("cpf", professor3.getCpf()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.content[0].cpf", is(professor3.getCpf())));
    }

    @Test
    @DisplayName("Teste de Pesquisa de professores por Id")
    void getProfessoresById() throws Exception {
        mockMvc.perform(
                get(ProfessorController.PATH + "/find").param("id", professor3.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.totalElements", Matchers.greaterThan(0)))
                .andExpect(jsonPath("$.content[0].id", is(professor3.getId())));
    }

    @Test
    @DisplayName("Teste de Pesquisa todos")
    void getProfessores() throws Exception {
        mockMvc.perform(
                get(ProfessorController.PATH + "/find"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.totalElements", Matchers.greaterThan(0)));
    }

    @Test
    @DisplayName("Teste de Pesquisa Sem resultado")
    void getProfessoresSemResultado() throws Exception {
        mockMvc.perform(
                get(ProfessorController.PATH + "/find").param("nome", "qualquercoisa tralala"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content", Matchers.hasSize(0)));
    }
}