package br.com.onboard.schoolquery.turma.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import br.com.onboard.schoolquery.disciplina.model.Disciplina;
import br.com.onboard.schoolquery.pessoa.repository.model.Aluno;

@ToString
@Getter
@Setter
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class Turma {
	@Id
	private Long Id;
	@NotNull
	@NotEmpty
	@Length(min = 1, max = 255)
	private String descricao;
	@NotNull
	@NotEmpty
	private int anoLetivo;
	@NotNull
	@NotEmpty
	private int periodoLetivo;
	@NotNull
	@NotEmpty
	private int numeroVagas;

	@ManyToMany(mappedBy = "turmas")
	private List<Aluno> alunos;

	@ManyToMany
	@JoinTable(
	        name="turma_disciplina",
	        joinColumns=
	            @JoinColumn(name="TURMA_ID", referencedColumnName="ID"),
	        inverseJoinColumns=
	            @JoinColumn(name="DISCIPLINA_ID", referencedColumnName="ID")
	    )
	private List<Disciplina> disciplinas;

	public Turma(@NotNull @NotEmpty @Length(min = 1, max = 255) String descricao, @NotNull @NotEmpty int anoLetivo,
			@NotNull @NotEmpty int periodoLetivo, @NotNull @NotEmpty int numeroVagas) {
		super();
		this.descricao = descricao;
		this.anoLetivo = anoLetivo;
		this.periodoLetivo = periodoLetivo;
		this.numeroVagas = numeroVagas;
	}

}
