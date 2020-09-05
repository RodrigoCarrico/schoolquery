package br.com.onboard.schoolquery.pessoa.professor.amqp.event;

import br.com.onboard.schoolquery.pessoa.enums.Titulacao;
import lombok.*;

import java.util.Set;

@Data
@Builder
public class ProfessorAlteradoEvent {
    public static final String NAME = "ProfessorAlteradoEvent";

    public static final String CONDITIONAL_EXPRESSION = "headers['type']=='" + NAME + "'";

    private final String id;
    private final String nome;
    private final String email;
    private final String cpf;
    private final Titulacao titulacao;

}

