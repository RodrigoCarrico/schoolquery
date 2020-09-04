package br.com.onboard.schoolquery.pessoa.aluno.api;

import br.com.onboard.schoolquery.pessoa.aluno.AlunoFactory;
import br.com.onboard.schoolquery.pessoa.aluno.model.Aluno;
import br.com.onboard.schoolquery.pessoa.aluno.repository.AlunoRepository;
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
class AlunoControllerTest {

    private AlunoFactory alunoFactoryTest;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private MockMvc mockMvc;

    private static final Aluno aluno1 = AlunoFactory.buildAluno1();

    @BeforeAll
    void setup() {
        AlunoFactory.salvaBD(aluno1, alunoRepository);
    }

    @Test
    @DisplayName("Teste de Pesquisa de Alunoes por nome")
    void deveRetornarOAlunoPeloNome() throws Exception {
        mockMvc.perform(
                get(AlunoController.PATH + "/find").param("nome", aluno1.getNome()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.content[0].nome", is(aluno1.getNome())));
    }

    @Test
    @DisplayName("Teste de Pesquisa de Alunoes por email")
    void deveRetornarOAlunoPeloEmail() throws Exception {
        mockMvc.perform(
                get(AlunoController.PATH + "/find").param("email", aluno1.getEmail()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.content[0].email", is(aluno1.getEmail())));
    }

    @Test
    @DisplayName("Teste de Pesquisa de Alunoes por Matricula")
    void deveRetornarOAlunopelaMatricula() throws Exception {
        mockMvc.perform(
                get(AlunoController.PATH + "/find").param("matricula", String.valueOf(aluno1.getMatricula())))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.totalElements", Matchers.greaterThan(0)))
                .andExpect(jsonPath("$.content[0].matricula", is(aluno1.getMatricula())));
    }

    @Test
    @DisplayName("Teste de Pesquisa de Alunoes por cpf")
    void deveRetornarOAlunoPeloCpf() throws Exception {
        mockMvc.perform(
                get(AlunoController.PATH + "/find").param("cpf", aluno1.getCpf()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.content[0].cpf", is(aluno1.getCpf())));
    }

    @Test
    @DisplayName("Teste de Pesquisa de Alunoes por Id")
    void deveRetornarOAlunoesPeloId() throws Exception {
        mockMvc.perform(
                get(AlunoController.PATH + "/find").param("id", aluno1.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.totalElements", Matchers.greaterThan(0)))
                .andExpect(jsonPath("$.content[0].id", is(aluno1.getId())));
    }

    @Test
    @DisplayName("Teste de Pesquisa todos")
    void deveRetornarTodosOsAlunoes() throws Exception {
        mockMvc.perform(
                get(AlunoController.PATH + "/find"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.totalElements", Matchers.greaterThan(0)));
    }

    @Test
    @DisplayName("Teste de Pesquisa Sem resultado")
    void deveRetornarAlunoesSemResultado() throws Exception {
        mockMvc.perform(
                get(AlunoController.PATH + "/find").param("nome", "qualquercoisa tralala"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content", Matchers.hasSize(0)));
    }

    @Test
    @DisplayName("Teste de Pesquisa de Alunoes por Forma de ingresso")
    void deveRetornarAlunosPelaFormarDeIngressoTitulacao() throws Exception {
        mockMvc.perform(
                get(AlunoController.PATH + "/find").param("formaIngresso", aluno1.getFormaIngresso().toString()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.totalElements", Matchers.greaterThan(0)))
                .andExpect(jsonPath("$.content[0].formaIngresso", is(aluno1.getFormaIngresso().toString())));
    }

    @Test
    @DisplayName("Teste de Pesquisa de param incorreto")
    void deveRetorarZeroQuandoParametroErrado() throws Exception {
        mockMvc.perform(
                get(AlunoController.PATH + "/find").param("teste", "teste"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.totalElements", Matchers.greaterThan(0)));
    }

}
