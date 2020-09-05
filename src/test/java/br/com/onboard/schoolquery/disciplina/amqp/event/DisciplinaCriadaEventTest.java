package br.com.onboard.schoolquery.disciplina.amqp.event;

import br.com.onboard.schoolquery.disciplina.model.Disciplina;
import br.com.onboard.schoolquery.pessoa.professor.repository.ProfessorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DisciplinaCriadaEventTest {
    @Autowired
    ProfessorRepository professorRepository;

    @Test
    void deveTestarDisciplinaSemProfessor() {
        var event = DisciplinaCriadaEvent.builder()
                .cargaHoraria(100)
                .descricao("teste")
                .sigla("A")
                .build();

        var disciplina = Disciplina.builder()
                .sigla(event.getSigla())
                .descricao(event.getDescricao())
                .professor(event.from(professorRepository))
                .cargaHoraria(event.getCargaHoraria())
                .build();

        assertThat(event.getCargaHoraria()).isEqualTo(disciplina.getCargaHoraria());
        assertThat(event.getDescricao()).isEqualTo(disciplina.getDescricao());
        assertThat(event.getSigla()).isEqualTo(disciplina.getSigla());

        assertThat(disciplina.getProfessor()).isNull();

    }

    @Test
    void deveTestarDisciplinaComProfessorNaoExisteBanco() {
        var event = DisciplinaCriadaEvent.builder()
                .cargaHoraria(100)
                .descricao("teste")
                .sigla("A")
                .professorId("1")
                .build();

        var disciplina = Disciplina.builder()
                .sigla(event.getSigla())
                .descricao(event.getDescricao())
                .professor(event.from(professorRepository))
                .cargaHoraria(event.getCargaHoraria())
                .build();

        assertThat(event.getCargaHoraria()).isEqualTo(disciplina.getCargaHoraria());
        assertThat(event.getDescricao()).isEqualTo(disciplina.getDescricao());
        assertThat(event.getSigla()).isEqualTo(disciplina.getSigla());

        assertThat(disciplina.getProfessor()).isNull();

    }

}