package br.com.onboard.schoolquery.pessoa.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@DynamicUpdate
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@IdClass(ProfessorDisciplinaId.class)
@Table(name = "professor_disciplina")
public class ProfessorDisciplina {
    @Id
    @Column(name = "disciplina_id")
    private String disciplinaId;

    @Id
    @NotNull
    @Column(name = "professor_id")
    private String professorId;

}
