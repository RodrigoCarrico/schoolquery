package br.com.onboard.schoolquery.pessoa.repository.view;

import br.com.onboard.schoolquery.disciplina.model.Disciplina;
import br.com.onboard.schoolquery.pessoa.enums.Titulacao;
import br.com.onboard.schoolquery.pessoa.repository.model.Pessoa;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@ToString(callSuper = true)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor()
@Table(name = "professor")
@Entity
public class ProfessorView extends Pessoa {

    @NotNull
    @Enumerated(EnumType.STRING)
    private Titulacao titulacao;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "professor_id", nullable = false, insertable = false)
    private Set<Disciplina> disciplinas;

    @Builder
    public ProfessorView(String id, String nome, String email, String cpf, Titulacao titulacao, Set<Disciplina> disciplinas) {
        super(id, nome, email, cpf);
        this.titulacao = titulacao;
        this.disciplinas = disciplinas;
    }


}
