package br.com.onboard.schoolquery.disciplina.model;

import br.com.onboard.schoolquery.pessoa.professor.model.Professor;
import br.com.onboard.schoolquery.turma.model.Turma;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Disciplina {

    @Id
    private String id;
    @NotNull
    @NotEmpty
    @Length(min = 1, max = 100)
    private String descricao;
    @NotNull
    @NotEmpty
    @Length(min = 1, max = 2)
    private String sigla;
    @NotNull
    @NotEmpty
    private Integer cargaHoraria;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "professor_id")
    @Setter
    private Professor professor;

    @ManyToMany(mappedBy = "disciplinas")
    private List<Turma> turmas;

    public Disciplina(String id, @NotNull @NotEmpty @Length(min = 1, max = 100) String descricao,
                      @NotNull @NotEmpty @Length(min = 1, max = 2) String sigla,
                      @NotNull @NotEmpty Integer cargaHoraria,
                      Professor professor) {
        this.id = id;
        this.descricao = descricao;
        this.sigla = sigla;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
    }
}
