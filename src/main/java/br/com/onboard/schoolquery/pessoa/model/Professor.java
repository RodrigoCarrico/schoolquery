package br.com.onboard.schoolquery.pessoa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.com.onboard.schoolquery.disciplina.model.Disciplina;
import br.com.onboard.schoolquery.pessoa.enums.Titulacao;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor()
public class Professor extends Pessoa {

	private static final long serialVersionUID = -3439939244086390654L;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Titulacao titulacao;

	@OneToMany(mappedBy = "professor", fetch = FetchType.EAGER)
	private List<Disciplina> disciplinas = new ArrayList<>();

	public Professor(String id, String nome, String email, String cpf, Titulacao titulacao) {
		super(id, nome, email, cpf);
		this.titulacao = titulacao;
	}


}
