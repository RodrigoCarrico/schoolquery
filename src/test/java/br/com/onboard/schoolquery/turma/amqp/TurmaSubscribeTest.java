package br.com.onboard.schoolquery.turma.amqp;

import br.com.onboard.schoolquery.FactoryQueueTest;
import br.com.onboard.schoolquery.disciplina.DisciplinaFactory;
import br.com.onboard.schoolquery.disciplina.model.Disciplina;
import br.com.onboard.schoolquery.disciplina.repository.DisciplinaRepository;
import br.com.onboard.schoolquery.pessoa.professor.ProfessorFactory;
import br.com.onboard.schoolquery.pessoa.professor.model.Professor;
import br.com.onboard.schoolquery.pessoa.professor.repository.ProfessorRepository;
import br.com.onboard.schoolquery.turma.amqp.event.TurmaCriadaEvent;
import br.com.onboard.schoolquery.turma.model.Turma;
import br.com.onboard.schoolquery.turma.repository.TurmaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TurmaSubscribeTest {

    @Autowired
    TurmaRepository turmaRepository;

    @Autowired
    FactoryQueueTest factoryQueueTest;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    private Disciplina disciplina;

    private final String id = UUID.randomUUID().toString();
    private final Integer anoLetivo = 2020;
    private final Integer periodoLetivo = 2;
    private final String descricao = "matutina";
    private final Integer numeroVagas = 20;


    @BeforeAll
    void setup() {
        Professor professor = ProfessorFactory.buildProfessor1();
        ProfessorFactory.salvaBD(professor, professorRepository);
        disciplina = DisciplinaFactory.buildDisciplina1(professor);
        DisciplinaFactory.salvaBD(disciplina, disciplinaRepository);
    }

    @Test
    @DisplayName("Testa criação de registro Turma na base de dados da Query")
    void devCriarTurmaNaBaseDeDados() {
        String id = UUID.randomUUID().toString();
        Set<TurmaCriadaEvent.TurmaDisciplinaCriadaEvent> disciplinas = new HashSet<>();
        disciplinas.add(TurmaCriadaEvent.TurmaDisciplinaCriadaEvent.from(disciplina.getId()));
        var event = TurmaCriadaEvent.builder()
                .id(id)
                .anoLetivo(anoLetivo)
                .periodoLetivo(periodoLetivo)
                .descricao(descricao)
                .disciplinas(disciplinas)
                .numeroVagas(numeroVagas)
                .build();


        factoryQueueTest.criaNovoRegistroFila(event);

        Turma turmaResult = turmaRepository.findById(id).get();
        assertThat(turmaResult).isNotNull();
        assertThat(turmaResult.getId()).isEqualTo(id);
        assertThat(turmaResult.getDescricao()).isEqualTo(descricao);
        assertThat(turmaResult.getAnoLetivo()).isEqualTo(anoLetivo);
        assertThat(turmaResult.getNumeroVagas()).isEqualTo(numeroVagas);
        assertThat(turmaResult.getDisciplinas().size()).isEqualTo(disciplinas.size());
    }
}
