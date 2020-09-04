package br.com.onboard.schoolquery.pessoa.aluno;


import br.com.onboard.schoolquery.pessoa.aluno.model.Aluno;
import br.com.onboard.schoolquery.pessoa.aluno.repository.AlunoRepository;
import br.com.onboard.schoolquery.pessoa.enums.FormaIngresso;

import java.util.UUID;


public class AlunoFactory {

    private static final String id = UUID.randomUUID().toString();
    private static final String cpf = "00000000003";
    private static final String email = "teste3@teste3.com.br";
    private static final String nome = "teste3 de inclusao3 de aluno3";
    private static final FormaIngresso formaIngresso = FormaIngresso.ENADE;
    private static final Integer matricula = 1234;


    private static final String id2 = UUID.randomUUID().toString();
    private static final String cpf2 = "11111111112";
    private static final String email2 = "teste2@teste2.com.br";
    private static final String nome2 = "teste2 de inclusao2 de Aluno2";
    private static final FormaIngresso formaIngresso2 = FormaIngresso.VESTIBULAR;
    private static final Integer matricula2 = 4321;

    public static Aluno buildAluno1() {
        return Aluno.builder()
                .cpf(cpf)
                .email(email)
                .id(id)
                .nome(nome)
                .matricula(matricula)
                .formaIngresso(formaIngresso)
                .build();
    }

    public static Aluno buildAluno2() {
        return Aluno.builder()
                .cpf(cpf2)
                .email(email2)
                .id(id2)
                .nome(nome2)
                .matricula(matricula2)
                .formaIngresso(formaIngresso2)
                .build();
    }

    public static void salvaBD(Aluno aluno, AlunoRepository alunoRepository) {
        alunoRepository.save(aluno);
    }

}

