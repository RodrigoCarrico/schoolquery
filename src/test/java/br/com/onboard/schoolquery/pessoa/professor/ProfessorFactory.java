package br.com.onboard.schoolquery.pessoa.professor;

import br.com.onboard.schoolquery.pessoa.enums.Titulacao;
import br.com.onboard.schoolquery.pessoa.professor.repository.ProfessorRepository;
import br.com.onboard.schoolquery.pessoa.professor.model.Professor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

public class ProfessorFactory {

    private static final String id = UUID.randomUUID().toString();
    private static final String cpf = "00000000003";
    private static final String email = "teste3@teste3.com.br";
    private static final String nome = "teste3 de inclusao3 de professor";
    private static final Titulacao titulacao = Titulacao.PHD;


    private static final String id2 = UUID.randomUUID().toString();
    private static final String cpf2 = "11111111112";
    private static final String email2 = "teste2@teste2.com.br";
    private static final String nome2 = "teste2 de inclusao2 de professor2";
    private static final Titulacao titulacao2 = Titulacao.MESTRE;

    public static Professor buildProfessor1(){
       return Professor.builder()
                .cpf(cpf)
                .email(email)
                .id(id)
                .nome(nome)
                .titulacao(titulacao)
                .build();


    }

    public static Professor buildProfessor2(){
        return Professor.builder()
                .cpf(cpf2)
                .email(email2)
                .id(id2)
                .nome(nome2)
                .titulacao(titulacao2)
                .build();
    }

    public static void salvaBD(Professor professor , ProfessorRepository professorRepository) {
        professorRepository.save(professor);
    }

}
