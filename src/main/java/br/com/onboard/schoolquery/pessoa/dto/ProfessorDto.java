package br.com.onboard.schoolquery.pessoa.dto;

import br.com.onboard.schoolquery.disciplina.dto.DisciplinaDto;
import br.com.onboard.schoolquery.pessoa.enums.Titulacao;
import br.com.onboard.schoolquery.pessoa.model.Professor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProfessorDto {
    private String id;
    private String nome;
    private String email;
    private String cpf;
    private Titulacao titulacao;

    private Set<DisciplinaDto> disciplinas;

    public ProfessorDto(Professor professor) {
        this.id = professor.getId();
        this.nome = professor.getNome();
        this.email = professor.getEmail();
        this.cpf = professor.getCpf();
        this.titulacao = professor.getTitulacao();
        this.disciplinas = (Set) professor.getDisciplinas();
    }

    public Professor converter() {
        Professor professor = new Professor(this.id, this.nome, this.email, this.cpf, this.titulacao);
        return professor;
    }

    public static Page<ProfessorDto> from(Page<Professor> professores) {
        return professores.map(ProfessorDto::new);
    }


}
