package br.com.onboard.schoolquery.pessoa.amqp;

import br.com.onboard.schoolquery.config.amqp.SCHOOLChannel;
import br.com.onboard.schoolquery.pessoa.amqp.event.ProfessorCriadoEvent;
import br.com.onboard.schoolquery.pessoa.repository.service.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@AllArgsConstructor
@EnableBinding(SCHOOLChannel.SchoolExchangeInput.class)
public class ProfessorSubscribe {

    @Autowired
    ProfessorService professorService;

    @StreamListener(target = SCHOOLChannel.SCHOOL_INPUT, condition = ProfessorCriadoEvent.CONDITIONAL_EXPRESSION)
    public void ProfessorCriado(ProfessorCriadoEvent message) {
        System.out.println("Teste de MESA" + message.toString());
        professorService.on(message);

    }

}
