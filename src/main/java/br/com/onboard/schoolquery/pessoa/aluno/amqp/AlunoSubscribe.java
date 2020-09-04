package br.com.onboard.schoolquery.pessoa.aluno.amqp;

import br.com.onboard.schoolquery.config.amqp.SCHOOLChannel;
import br.com.onboard.schoolquery.pessoa.aluno.amqp.event.AlunoAlteradoEvent;
import br.com.onboard.schoolquery.pessoa.aluno.amqp.event.AlunoCriadoEvent;
import br.com.onboard.schoolquery.pessoa.aluno.service.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
@AllArgsConstructor
@EnableBinding(SCHOOLChannel.SchoolExchangeInput.class)
public class AlunoSubscribe {

    @Autowired
    AlunoService alunoService;

    @StreamListener(target = SCHOOLChannel.SCHOOL_INPUT, condition = AlunoCriadoEvent.CONDITIONAL_EXPRESSION)
    public void AlunoCriado(AlunoCriadoEvent message) {
        System.out.println("Aluno subscribe Criando: " + message.toString());
        alunoService.on(message);

    }

    @StreamListener(target = SCHOOLChannel.SCHOOL_INPUT, condition = AlunoAlteradoEvent.CONDITIONAL_EXPRESSION)
    public void AlunoAlterado(AlunoAlteradoEvent message) {
        System.out.println("Aluno subscribe Alterado: " + message.toString());
        alunoService.on(message);
    }

}

