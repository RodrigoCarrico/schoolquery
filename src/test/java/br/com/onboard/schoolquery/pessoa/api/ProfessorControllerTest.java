package br.com.onboard.schoolquery.pessoa.api;

import br.com.onboard.schoolquery.pessoa.ProfessorFactoryTest;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProfessorControllerTest {

    @Autowired
    private ProfessorFactoryTest professorFactoryTest;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    private void setup() {
        professorFactoryTest.salvaBD();
    }

    @Test
    @DisplayName("Teste de Pesquisa de professores por nome")
    public void getProfessoresByNome() throws Exception {
        mockMvc.perform(
                get(ProfessorController.PATH + "/find").param("nome", professorFactoryTest.getNome()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.content[0].nome", is(professorFactoryTest.getNome())));
    }

    @Test
    @DisplayName("Teste de Pesquisa de professores por email")
    public void getProfessoresByEmail() throws Exception {
        mockMvc.perform(
                get(ProfessorController.PATH + "/find").param("email", professorFactoryTest.getEmail()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.content[0].email", is(professorFactoryTest.getEmail())));
    }

    @Test
    @DisplayName("Teste de Pesquisa de professores por Titulacao")
    public void getProfessoresByTitulacao() throws Exception {
        mockMvc.perform(
                get(ProfessorController.PATH + "/find").param("titulacao", professorFactoryTest.getTitulacao().name()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.totalElements", Matchers.greaterThan(0)))
                .andExpect(jsonPath("$.content[0].titulacao", is(professorFactoryTest.getTitulacao().name())));
    }

    @Test
    @DisplayName("Teste de Pesquisa de professores por cpf")
    public void getProfessoresByCpf() throws Exception {
        mockMvc.perform(
                get(ProfessorController.PATH + "/find").param("cpf", professorFactoryTest.getCpf()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.content[0].cpf", is(professorFactoryTest.getCpf())));
    }

    @Test
    @DisplayName("Teste de Pesquisa de professores por Id")
    public void getProfessoresById() throws Exception {
        mockMvc.perform(
                get(ProfessorController.PATH + "/find").param("id", professorFactoryTest.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.totalElements", Matchers.greaterThan(0)))
                .andExpect(jsonPath("$.content[0].id", is(professorFactoryTest.getId())));
    }

    @Test
    @DisplayName("Teste de Pesquisa todos")
    public void getProfessores() throws Exception {
        mockMvc.perform(
                get(ProfessorController.PATH + "/find"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.totalElements", Matchers.greaterThan(1)));
    }

    @Test
    @DisplayName("Teste de Pesquisa Sem resultado")
    public void getProfessoresSemResultado() throws Exception {
        mockMvc.perform(
                get(ProfessorController.PATH + "/find").param("nome","qualquercoisa tralala"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content", Matchers.hasSize(0)));
    }
}