package br.com.onboard.schoolquery.utils.amqp;

import br.com.onboard.schoolquery.pessoa.dto.AlunoDto;
import br.com.onboard.schoolquery.pessoa.dto.ProfessorDto;
import br.com.onboard.schoolquery.pessoa.service.AlunoService;
import br.com.onboard.schoolquery.pessoa.service.ProfessorService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

// import lombok.extern.slf4j.Slf4j;

//@Slf4j
@Service
public class SubscribeService {

    @Autowired
    ProfessorService professorService;

    @Autowired
    AlunoService alunoService;

    @RabbitListener(queues = {"${queue.school.name}"})
    public void receive(@Header(value = "tableId") String table,
                        @Payload String message) {
        System.out.println("Bytes: " + message + " : " + table);
        switch (table) {
            case "Professor":
                professorService.salvar(ProfessorDto.toProfessor(message));
                break;
            case "Aluno":
                alunoService.salvar(AlunoDto.toAluno(message));
                break;
            default:
                break;
        }
    }

    // @RabbitHandler
    // public void receive(Professor professor) {
    // log.info("Consumo: " + fileBody);
    // System.out.println("Professor: " + professor.toString());
    // Professor pro = (Professor) message;
    // System.out.println("Mensagem2: " + pro.toString());
    // var object = messageConverter.fromMessage(message);
    // System.out.println("Consumo: " + message.getClass().getName());
    // }

}
