package br.com.onboard.schoolquery.pessoa.repository.model;

import br.com.onboard.schoolquery.disciplina.model.Disciplina;
import br.com.onboard.schoolquery.pessoa.enums.Titulacao;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.repository.query.SpelQueryContext;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor()
@Entity
@Table(name="professor")
public class Professor extends Pessoa {

    private static final long serialVersionUID = -3439939244086390654L;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Titulacao titulacao;

    @JsonManagedReference
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SELECT)
    private Set<Disciplina> disciplinas;

    @Builder
    public Professor(String id, String nome, String email, String cpf, Titulacao titulacao, Set<Disciplina> disciplinas) {
        super(id, nome, email, cpf);
        this.titulacao = titulacao;
        if (disciplinas !=null && !disciplinas.isEmpty()) {
            this.disciplinas = disciplinas;
        }
    }


}
