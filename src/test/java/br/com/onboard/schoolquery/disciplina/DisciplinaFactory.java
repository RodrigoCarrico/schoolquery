package br.com.onboard.schoolquery.disciplina;

import br.com.onboard.schoolquery.disciplina.model.Disciplina;
import br.com.onboard.schoolquery.disciplina.repository.DisciplinaRepository;
import br.com.onboard.schoolquery.pessoa.professor.model.Professor;

import java.util.UUID;

public class DisciplinaFactory {

    private static final String id = UUID.randomUUID().toString();
    private static final String descricao = "Matematica";
    private static final String sigla = "M";
    private static final Integer cargaHoraria = 100;

    private static final String id2 = UUID.randomUUID().toString();
    private static final String descricao2 = "Português";
    private static final String sigla2 = "P";
    private static final Integer cargaHoraria2 = 200;

    public static Disciplina buildDisciplina1(Professor professor) {
        return Disciplina.builder()
                .id(id)
                .professor(professor)
                .descricao(descricao)
                .cargaHoraria(cargaHoraria)
                .sigla(sigla)
                .build();
    }

    public static Disciplina buildDisciplina2(Professor professor2) {
        return Disciplina.builder()
                .id(id2)
                .professor(professor2)
                .descricao(descricao2)
                .cargaHoraria(cargaHoraria2)
                .sigla(sigla2)
                .build();
    }

    public static void salvaBD(Disciplina disciplina, DisciplinaRepository disciplinaRepository) {
        disciplinaRepository.save(disciplina);
    }

}