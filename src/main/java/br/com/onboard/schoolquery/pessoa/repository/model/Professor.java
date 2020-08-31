package br.com.onboard.schoolquery.pessoa.repository.model;

import br.com.onboard.schoolquery.pessoa.enums.Titulacao;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@ToString(callSuper = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor()
@Entity
public class Professor extends Pessoa {

    private static final long serialVersionUID = -3439939244086390654L;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Titulacao titulacao;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "professor_id", nullable = false, insertable = false)
    private Set<ProfessorDisciplina> disciplinas;

    @Builder
    public Professor(String id, String nome, String email, String cpf, Titulacao titulacao, Set<ProfessorDisciplina> disciplinas) {
        super(id, nome, email, cpf);
        this.titulacao = titulacao;
        this.disciplinas = disciplinas;
    }


}
