package br.com.onboard.schoolquery.pessoa.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
	@JoinTable(name = "aluno_turma", joinColumns = @JoinColumn(name = "ALUNO_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "TURMA_ID", referencedColumnName = "ID"))
	private List<Turma> turmas;

	@Builder
	public Aluno(String id, String nome, String email, String cpf, Integer matricula, FormaIngresso formaIngresso) {
		super(id, nome, email, cpf);
		this.formaIngresso = formaIngresso;
		this.matricula = matricula;
	}

}
