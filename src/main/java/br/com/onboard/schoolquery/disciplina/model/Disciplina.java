package br.com.onboard.schoolquery.disciplina.model;

import br.com.onboard.schoolquery.pessoa.repository.model.Professor;
import br.com.onboard.schoolquery.pessoa.repository.model.ProfessorDisciplina;
import br.com.onboard.schoolquery.turma.model.Turma;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@ToString
@Getter
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class Disciplina {
	
	@Id
	private String id;
	@NotNull @NotEmpty @Length(min=1, max = 100)
	private String descricao;
	@NotNull @NotEmpty @Length(min=1, max = 2)
	private String sigla;
	@NotNull @NotEmpty
	private int cargaHoraria;
	
	//@ManyToOne
	//private Professor professor;
	
	@ManyToMany(mappedBy = "disciplinas")
	private List<Turma> turmas;
	
	public Disciplina(@NotNull @NotEmpty @Length(min = 1, max = 100) String descricao,
					  @NotNull @NotEmpty @Length(min = 1, max = 2) String sigla,
					  @NotNull @NotEmpty int cargaHoraria){
			//Professor professor) {
		this.descricao = descricao;
		this.sigla = sigla;
		this.cargaHoraria = cargaHoraria;
		//this.professor = professor;
	}
	
	
	

}
