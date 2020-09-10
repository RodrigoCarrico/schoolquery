package br.com.onboard.schoolquery.turma.amqp.event;

import br.com.onboard.schoolquery.disciplina.model.Disciplina;
import br.com.onboard.schoolquery.disciplina.repository.DisciplinaRepository;
import br.com.onboard.schoolquery.pessoa.aluno.amqp.event.AlunoCriadoEvent;
import br.com.onboard.schoolquery.pessoa.enums.FormaIngresso;
import br.com.onboard.schoolquery.turma.model.Turma;

import lombok.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Data
@Builder
public class TurmaCriadaEvent {
    public static final String NAME = "TurmaCriadaEvent";

    public static final String CONDITIONAL_EXPRESSION = "headers['type']=='" + NAME + "'";

    private final String id;
    private final String descricao;
    private final Integer anoLetivo;
    private final Integer periodoLetivo;
    private final Integer numeroVagas;
    private final Set<TurmaDisciplinaCriadaEvent> disciplinas;

    public Set<Disciplina> getDisciplinaEvent(DisciplinaRepository disciplinaRepository) {
        Set<Disciplina> disciplinas = new HashSet<>();
        if (nonNull(this.getDisciplinas())) {
            disciplinas = this.getDisciplinas()
                    .stream()
                    .map(disciplina -> {
                        Optional<Disciplina> optional = disciplinaRepository.findById(disciplina.getDisciplinaId());
                        if (optional.isPresent()) {
                            return optional.get();
                        } else {
                            return new Disciplina();
                        }
                    })
                    .collect(Collectors.toSet()).stream()
                    .filter(t -> nonNull(t.getId())).collect(Collectors.toSet());
        }
        return disciplinas;
    }

    @Getter
    @AllArgsConstructor(staticName = "from")
    @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
    public static final class TurmaDisciplinaCriadaEvent {
        private final String disciplinaId;
    }
}
