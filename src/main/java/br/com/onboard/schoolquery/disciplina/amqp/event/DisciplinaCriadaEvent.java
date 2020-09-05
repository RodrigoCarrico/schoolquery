package br.com.onboard.schoolquery.disciplina.amqp.event;

import br.com.onboard.schoolquery.pessoa.professor.model.Professor;
import br.com.onboard.schoolquery.pessoa.professor.repository.ProfessorRepository;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Getter
@Builder()
public class DisciplinaCriadaEvent {
    public static final String NAME = "DisciplinaCriadaEvent";

    public static final String CONDITIONAL_EXPRESSION = "headers['type']=='" + NAME + "'";

    private String id;
    private String descricao;
    private String sigla;
    private Integer cargaHoraria;
    private String professorId;

    public Professor from(ProfessorRepository professorRepository) {
        if (this.professorId == null) return null;

        Optional<Professor> optional = professorRepository.findById(this.professorId);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
