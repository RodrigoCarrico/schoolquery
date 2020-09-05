package br.com.onboard.schoolquery.disciplina.amqp;

import br.com.onboard.schoolquery.FactoryQueueTest;
import br.com.onboard.schoolquery.disciplina.DisciplinaFactory;
import br.com.onboard.schoolquery.disciplina.amqp.event.DisciplinaCriadaEvent;
import br.com.onboard.schoolquery.disciplina.model.Disciplina;
import br.com.onboard.schoolquery.disciplina.repository.DisciplinaRepository;
import br.com.onboard.schoolquery.pessoa.professor.ProfessorFactory;
import br.com.onboard.schoolquery.pessoa.professor.model.Professor;
import br.com.onboard.schoolquery.pessoa.professor.repository.ProfessorRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DisciplinaSubscribeTest {
    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    FactoryQueueTest factoryQueueTest;

    @Autowired
    ProfessorRepository professorRepository;

    private Disciplina disciplina;


    @BeforeAll
    void setup() {
        Professor professor = ProfessorFactory.buildProfessor1();
        ProfessorFactory.salvaBD(professor, professorRepository);
        disciplina = DisciplinaFactory.buildDisciplina1(professor);
    }

    @Test
    @DisplayName("Testa criação de registro Disciplina na base de dados da Query")
    void devCriarDisciplinaNaBaseDeDados() {

        var event = DisciplinaCriadaEvent.builder()
                .professorId(disciplina.getProfessor().getId())
                .descricao(disciplina.getDescricao())
                .cargaHoraria(disciplina.getCargaHoraria())
                .sigla(disciplina.getSigla())
                .id(disciplina.getId())
                .build();

        factoryQueueTest.criaNovoRegistroFila(event);

        Disciplina disciplinaResult = disciplinaRepository.findById(disciplina.getId()).get();
        assertThat(disciplinaResult).isNotNull();
        assertThat(disciplinaResult.getId()).isEqualTo(disciplina.getId());
        assertThat(disciplinaResult.getDescricao()).isEqualTo(disciplina.getDescricao());
        assertThat(disciplinaResult.getProfessor().getId()).isEqualTo(disciplina.getProfessor().getId());
        assertThat(disciplinaResult.getSigla()).isEqualTo(disciplina.getSigla());
        assertThat(disciplinaResult.getCargaHoraria()).isEqualTo(disciplina.getCargaHoraria());
    }
}