package br.com.onboard.schoolquery.turma.amqp;

import br.com.onboard.schoolquery.config.amqp.SCHOOLChannel;
import br.com.onboard.schoolquery.pessoa.aluno.amqp.event.AlunoCriadoEvent;
import br.com.onboard.schoolquery.pessoa.aluno.service.AlunoService;
import br.com.onboard.schoolquery.turma.amqp.event.TurmaCriadaEvent;
import br.com.onboard.schoolquery.turma.service.TurmaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@AllArgsConstructor
@EnableBinding(SCHOOLChannel.SchoolExchangeInput.class)
public class TurmaSubscribe {
    @Autowired
    TurmaService turmaService;

    @StreamListener(target = SCHOOLChannel.SCHOOL_INPUT, condition = TurmaCriadaEvent.CONDITIONAL_EXPRESSION)
    public void TurmaCriado(TurmaCriadaEvent message) {
        System.out.println("Turma subscribe Criada: " + message.toString());
        turmaService.on(message);

    }
}
