package br.com.onboard.schoolquery.pessoa.professor.amqp;

import br.com.onboard.schoolquery.FactoryQueueTest;
import br.com.onboard.schoolquery.pessoa.professor.amqp.event.ProfessorAlteradoEvent;
import br.com.onboard.schoolquery.pessoa.professor.amqp.event.ProfessorCriadoEvent;
import br.com.onboard.schoolquery.pessoa.enums.Titulacao;
import br.com.onboard.schoolquery.pessoa.professor.repository.ProfessorRepository;
import br.com.onboard.schoolquery.pessoa.professor.model.Professor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProfessorSubscribeTest {

    @Autowired
    FactoryQueueTest factoryQueueTest;

    @Autowired
    ProfessorRepository professorRepository;

    private final String id = UUID.randomUUID().toString();
    private final String cpf = "00000000000";
    private final String email = "teste@teste.com.br";
    private final String nome = "teste de inclusao de professor";
    private final Titulacao titulacao = Titulacao.DOUTOR;

    @Test
    @DisplayName("Testa criação de registro Professor na base de dados da Query")
    void deveCriarProfessorNaBaseDeDados() {
        var event = ProfessorCriadoEvent.builder()
                .cpf(cpf)
                .email(email)
                .id(id)
                .nome(nome)
                .titulacao(titulacao)
                .build();

        factoryQueueTest.criaNovoRegistroFila(event);

        Professor professor = professorRepository.findById(id).get();
        assertThat(professor).isNotNull();
        assertThat(professor.getId()).isEqualTo(id);
        assertThat(professor.getNome()).isEqualTo(nome);
        assertThat(professor.getEmail()).isEqualTo(email);
        assertThat(professor.getCpf()).isEqualTo(cpf);
        assertThat(professor.getTitulacao()).isEqualTo(titulacao);
    }

    @Test
    @DisplayName("Testa Alteração de registro Professor na base de dados da Query")
    void deveAlterarProfessorNaBaseDeDados() {
        Professor professor = Professor.builder().cpf(cpf)
                .email(email)
                .id(id)
                .nome(nome)
                .titulacao(titulacao)
                .build();

        professorRepository.save(professor);

        String cpfAltera = "11111111111";
        String nomeAltera = "Teste alteração";
        String emailAltera = "teste123@teste123.com.br";
        Titulacao titularAltera = Titulacao.PHD;

        var event = ProfessorAlteradoEvent.builder()
                .cpf(cpfAltera)
                .email(emailAltera)
                .id(id)
                .nome(nomeAltera)
                .titulacao(titularAltera)
                .build();

        factoryQueueTest.criaNovoRegistroFila(event);

        Professor professorResult = professorRepository.findById(id).get();

        assertThat(professorResult).isNotNull();
        assertThat(professorResult.getId()).isEqualTo(id);
        assertThat(professorResult.getNome()).isEqualTo(nomeAltera);
        assertThat(professorResult.getEmail()).isEqualTo(emailAltera);
        assertThat(professorResult.getCpf()).isEqualTo(cpfAltera);
        assertThat(professorResult.getTitulacao()).isEqualTo(titularAltera);
    }
}
