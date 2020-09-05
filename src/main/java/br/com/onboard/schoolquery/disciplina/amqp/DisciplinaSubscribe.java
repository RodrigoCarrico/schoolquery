package br.com.onboard.schoolquery.disciplina.amqp;

import br.com.onboard.schoolquery.config.amqp.SCHOOLChannel;
import br.com.onboard.schoolquery.disciplina.amqp.event.DisciplinaCriadaEvent;
import br.com.onboard.schoolquery.disciplina.service.DisciplinaService;
import br.com.onboard.schoolquery.pessoa.aluno.amqp.event.AlunoCriadoEvent;
import br.com.onboard.schoolquery.pessoa.aluno.service.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@AllArgsConstructor
@EnableBinding(SCHOOLChannel.SchoolExchangeInput.class)
public class DisciplinaSubscribe {
    @Autowired
    DisciplinaService disciplinaService;

    @StreamListener(target = SCHOOLChannel.SCHOOL_INPUT, condition = DisciplinaCriadaEvent.CONDITIONAL_EXPRESSION)
    public void DisciplinaCriada(DisciplinaCriadaEvent message) {
        System.out.println("Disciplina subscribe Criando: " + message.toString());
        disciplinaService.on(message);

    }
}
