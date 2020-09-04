package br.com.onboard.schoolquery.pessoa.aluno.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import br.com.onboard.schoolquery.pessoa.repository.model.Pessoa;
import com.sun.istack.NotNull;

import br.com.onboard.schoolquery.pessoa.enums.FormaIngresso;
import br.com.onboard.schoolquery.turma.model.Turma;
import lombok.*;

@ToString
@Getter
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Aluno extends Pessoa {

	private static final long serialVersionUID = 2230527831023135281L;
	
	@NotNull
	private Integer matricula;
	@NotNull
	@Enumerated(EnumType.STRING)
	private FormaIngresso formaIngresso;

	@ManyToMany
	@JoinTable(name = "aluno_turma", joinColumns =
	@JoinColumn(name = "ALUNO_ID", referencedColumnName = "ID"),
			inverseJoinColumns = @JoinColumn(name = "TURMA_ID", referencedColumnName = "ID"))
	private Set<Turma> turmas;

	@Builder
	public Aluno(String id, String nome,
				 String email, String cpf,
				 Integer matricula, FormaIngresso formaIngresso,
				 Set<Turma> turmas) {
		super(id, nome, email, cpf);
		this.formaIngresso = formaIngresso;
		this.matricula = matricula;

		if(turmas!=null && turmas.size()> 0) {
			this.turmas = turmas;
		}
	}

}
