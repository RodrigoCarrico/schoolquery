package br.com.onboard.schoolquery.pessoa.aluno.amqp;

import br.com.onboard.schoolquery.FactoryQueueTest;
import br.com.onboard.schoolquery.pessoa.aluno.amqp.event.AlunoAlteradoEvent;
import br.com.onboard.schoolquery.pessoa.aluno.amqp.event.AlunoCriadoEvent;
import br.com.onboard.schoolquery.pessoa.aluno.model.Aluno;
import br.com.onboard.schoolquery.pessoa.aluno.repository.AlunoRepository;
import br.com.onboard.schoolquery.pessoa.enums.FormaIngresso;
import br.com.onboard.schoolquery.turma.model.Turma;
import br.com.onboard.schoolquery.turma.repository.TurmaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AlunoSubscribeTest {

    @Autowired
    FactoryQueueTest factoryQueueTest;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    TurmaRepository turmaRepository;

    private final String id = UUID.randomUUID().toString();
    private final String cpf = "00000000000";
    private final String email = "teste@teste.com.br";
    private final String nome = "teste de inclusao de professor";
    private final Integer matricula = 1234;
    private final FormaIngresso formaIngresso = FormaIngresso.VESTIBULAR;
    private final Set<AlunoCriadoEvent.AlunoTurmaCriadoEvent> turmas = new HashSet<>(
            Arrays.asList(AlunoCriadoEvent.AlunoTurmaCriadoEvent.from("1"), AlunoCriadoEvent.AlunoTurmaCriadoEvent.from("2"), AlunoCriadoEvent.AlunoTurmaCriadoEvent.from("3")));

    private final Set<AlunoAlteradoEvent.AlunoTurmaAlteradoEvent> turmas2 = new HashSet<>(
            Arrays.asList(AlunoAlteradoEvent.AlunoTurmaAlteradoEvent.from("1"), AlunoAlteradoEvent.AlunoTurmaAlteradoEvent.from("2"), AlunoAlteradoEvent.AlunoTurmaAlteradoEvent.from("3")));

    private final String turmaId = "1";
    private final String descricao = "Turma A";
    private final Integer anoLetivo = 2020;
    private final Integer periodoLetivo = 200;
    private final Integer numeroVagas = 60;

    private final String turmaId2 = "2";
    private final String descricao2 = "Turma B";
    private final Integer anoLetivo2 = 2020;
    private final Integer periodoLetivo2 = 200;
    private final Integer numeroVagas2 = 30;

    @Test
    @DisplayName("Testa criação de registro Aluno na base de dados da Query")
    void devCriarAlunoNaBaseDeDados() {
        Turma turma1 = Turma.builder()
                .anoLetivo(anoLetivo)
                .descricao(descricao)
                .id(turmaId)
                .numeroVagas(numeroVagas)
                .periodoLetivo(periodoLetivo)
                .build();

        turmaRepository.save(turma1);

        var event = AlunoCriadoEvent.builder()
                .cpf(cpf)
                .email(email)
                .id(id)
                .nome(nome)
                .formaIngresso(formaIngresso)
                .matricula(matricula)
                .turmas(turmas)
                .build();

        factoryQueueTest.criaNovoRegistroFila(event);

        Aluno aluno = alunoRepository.findById(id).get();
        assertThat(aluno).isNotNull();
        assertThat(aluno.getId()).isEqualTo(id);
        assertThat(aluno.getNome()).isEqualTo(nome);
        assertThat(aluno.getEmail()).isEqualTo(email);
        assertThat(aluno.getCpf()).isEqualTo(cpf);
        assertThat(aluno.getFormaIngresso()).isEqualTo(formaIngresso);
        assertThat(aluno.getMatricula()).isEqualTo(matricula);
    }


    @Test
    @DisplayName("Testa Alteração de registro Aluno na base de dados da Query")
    void deveAlterarAlunoNaBaseDeDados() {
        Turma turma2 = Turma.builder()
                .anoLetivo(anoLetivo2)
                .descricao(descricao2)
                .id(turmaId2)
                .numeroVagas(numeroVagas2)
                .periodoLetivo(periodoLetivo2)
                .build();

        turmaRepository.save(turma2);

        var event = AlunoAlteradoEvent.builder()
                .cpf(cpf)
                .email(email)
                .id(id)
                .nome(nome)
                .formaIngresso(formaIngresso)
                .matricula(matricula)
                .turmas(turmas2)
                .build();

        factoryQueueTest.criaNovoRegistroFila(event);

        Aluno aluno = alunoRepository.findById(id).get();
        assertThat(aluno).isNotNull();
        assertThat(aluno.getId()).isEqualTo(id);
        assertThat(aluno.getNome()).isEqualTo(nome);
        assertThat(aluno.getEmail()).isEqualTo(email);
        assertThat(aluno.getCpf()).isEqualTo(cpf);
        assertThat(aluno.getFormaIngresso()).isEqualTo(formaIngresso);
        assertThat(aluno.getMatricula()).isEqualTo(matricula);
    }
}