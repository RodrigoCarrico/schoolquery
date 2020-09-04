package br.com.onboard.schoolquery.pessoa.aluno.amqp.event;

import br.com.onboard.schoolquery.pessoa.enums.FormaIngresso;
import br.com.onboard.schoolquery.turma.model.Turma;
import br.com.onboard.schoolquery.turma.repository.TurmaRepository;
import lombok.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Getter
@Builder()
public class AlunoCriadoEvent {


    public static final String NAME = "AlunoCriadoEvent";

    public static final String CONDITIONAL_EXPRESSION = "headers['type']=='" + NAME + "'";

    private final String id;
    private final String nome;
    private final String email;
    private final String cpf;
    private final FormaIngresso formaIngresso;
    private final Integer matricula;
    private final Set<AlunoTurmaCriadoEvent> turmas;

    public Set<Turma> getTurmasEvent(TurmaRepository turmaRepository) {
        Set<Turma> turmas = new HashSet<>();
        if (nonNull(this.getTurmas())) {
            turmas = this.getTurmas()
                    .stream()
                    .map(turma -> {
                        Optional<Turma> optional = turmaRepository.findById(turma.getTurmaId());
                        if (optional.isPresent()) {
                            return optional.get();
                        } else {
                            return new Turma();
                        }
                    })
                    .collect(Collectors.toSet()).stream()
                    .filter(t -> nonNull(t.getId())).collect(Collectors.toSet());
        }
        return turmas;
    }

    @Getter
    @AllArgsConstructor(staticName = "from")
    @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
    public static final class AlunoTurmaCriadoEvent {
        private final String turmaId;
    }
}