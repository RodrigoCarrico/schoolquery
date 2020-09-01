package br.com.onboard.schoolquery.pessoa;

import br.com.onboard.schoolquery.pessoa.enums.Titulacao;
import br.com.onboard.schoolquery.pessoa.repository.ProfessorRepository;
import br.com.onboard.schoolquery.pessoa.repository.model.Professor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Getter
public class ProfessorFactoryTest {

    @Autowired
    ProfessorRepository professorRepository;

    private Professor professor1;
    private Professor professor2;

    private final String id = UUID.randomUUID().toString();
    private final String cpf = "00000000003";
    private final String email = "teste3@teste3.com.br";
    private final String nome = "teste3 de inclusao3 de professor";
    private final Titulacao titulacao = Titulacao.DOUTOR;


    private final String id2 = UUID.randomUUID().toString();
    private final String cpf2 = "11111111112";
    private final String email2 = "teste2@teste2.com.br";
    private final String nome2 = "teste2 de inclusao2 de professor2";
    private final Titulacao titulacao2 = Titulacao.MESTRE;

    public ProfessorFactoryTest() {
        professor1 = Professor.builder()
                .cpf(cpf)
                .email(email)
                .id(id)
                .nome(nome)
                .titulacao(titulacao)
                .build();

        professor2 = Professor.builder()
                .cpf(cpf2)
                .email(email2)
                .id(id2)
                .nome(nome2)
                .titulacao(titulacao2)
                .build();
    }

    public void salvaBD() {
        professorRepository.save(professor1);
        professorRepository.save(professor2);
    }

}
