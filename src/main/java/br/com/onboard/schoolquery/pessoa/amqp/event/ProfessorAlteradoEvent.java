package br.com.onboard.schoolquery.pessoa.amqp.event;

import br.com.onboard.schoolquery.pessoa.enums.Titulacao;
import lombok.*;

import java.util.Set;

@Data
@Builder(access = AccessLevel.PRIVATE)
public class ProfessorAlteradoEvent {
    public static final String NAME = "ProfessorAlteradoEvent";

    public static final String CONDITIONAL_EXPRESSION = "headers['type']=='" + NAME + "'";

    private final String id;
    private final String nome;
    private final String email;
    private final String cpf;
    private final Titulacao titulacao;
    private final Set<ProfessorDisciplinaEvent> disciplinas;

    @Getter
    @AllArgsConstructor(staticName = "from")
    @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
    public static final class ProfessorDisciplinaEvent {
        private final String disciplinaId;
    }
}

