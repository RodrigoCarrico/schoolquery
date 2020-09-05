package br.com.onboard.schoolquery.disciplina.dto;

import br.com.onboard.schoolquery.disciplina.model.Disciplina;
import br.com.onboard.schoolquery.pessoa.professor.model.Professor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class DisciplinaDto {
    private String id;
    private String descricao;
    private String sigla;
    private int cargaHoraria;
    private Professor professor;

    public DisciplinaDto(Disciplina disciplina) {
        this.id = disciplina.getId();
        this.descricao = disciplina.getDescricao();
        this.sigla = disciplina.getSigla();
        this.cargaHoraria = disciplina.getCargaHoraria();
        this.professor = disciplina.getProfessor();
    }

  /*  public static Set<DisciplinaDto> converter(Set<Disciplina> disciplinas) {
        return disciplinas.stream().map(DisciplinaDto::new).collect(Collectors.toSet());
    }*/

}
