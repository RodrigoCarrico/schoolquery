package br.com.onboard.schoolquery.disciplina.model;

import br.com.onboard.schoolquery.disciplina.dto.DisciplinaDto;
import br.com.onboard.schoolquery.pessoa.professor.model.Professor;
import br.com.onboard.schoolquery.turma.model.Turma;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "disciplina")
@NoArgsConstructor
public class Disciplina {
    @Id
    private String id;
    @NotNull
    @Length(min = 1, max = 100)
    private String descricao;
    @NotNull
    @Length(min = 1, max = 2)
    private String sigla;
    @NotNull
    private Integer cargaHoraria;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToMany(mappedBy = "disciplinas", fetch = FetchType.EAGER)
    private List<Turma> turmas;

    @Builder
    public Disciplina(String id, String descricao,
                      String sigla,
                      Integer cargaHoraria,
                      Professor professor) {
        this.id = id;
        this.descricao = descricao;
        this.sigla = sigla;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
    }

    public static Page<DisciplinaDto> from(Page<Disciplina> pageDisciplinas) {
        return pageDisciplinas.map(DisciplinaDto::new);
    }
}
