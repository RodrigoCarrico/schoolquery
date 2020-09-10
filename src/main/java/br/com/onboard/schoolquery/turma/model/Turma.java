package br.com.onboard.schoolquery.turma.model;

import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;

import br.com.onboard.schoolquery.disciplina.model.Disciplina;
import br.com.onboard.schoolquery.pessoa.aluno.model.Aluno;

@ToString
@Getter
@Setter
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class Turma {
	@Id
	private String id;
	@NotNull
	@Length(min = 1, max = 255)
	private String descricao;
	@NotNull
	private Integer anoLetivo;
	@NotNull
	private Integer periodoLetivo;
	@NotNull
	private Integer numeroVagas;

	@ManyToMany(mappedBy = "turmas",fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private Set<Aluno> alunos;

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinTable(
	        name="turma_disciplina",
	        joinColumns=
	            @JoinColumn(name="TURMA_ID", referencedColumnName="ID"),
	        inverseJoinColumns=
	            @JoinColumn(name="DISCIPLINA_ID", referencedColumnName="ID")
	    )
	private Set<Disciplina> disciplinas;
    @Builder
	public Turma(String id, @NotNull @NotEmpty @Length(min = 1, max = 255) String descricao, @NotNull Integer anoLetivo,
			@NotNull Integer periodoLetivo, @NotNull Integer numeroVagas, Set<Disciplina> disciplinas) {
    	this.id = id;
		this.descricao = descricao;
		this.anoLetivo = anoLetivo;
		this.periodoLetivo = periodoLetivo;
		this.numeroVagas = numeroVagas;
		this.disciplinas = disciplinas;
	}

}
