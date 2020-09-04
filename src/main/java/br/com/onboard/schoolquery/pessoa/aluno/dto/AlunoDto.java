package br.com.onboard.schoolquery.pessoa.aluno.dto;

import br.com.onboard.schoolquery.pessoa.enums.FormaIngresso;
import br.com.onboard.schoolquery.pessoa.aluno.model.Aluno;
import br.com.onboard.schoolquery.turma.dto.TurmaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AlunoDto {
    private String id;
    private String nome;
    private String email;
    private String cpf;
    private Integer matricula;
    private FormaIngresso formaIngresso;

    private Set<TurmaDto> turmas;

    public AlunoDto(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.email = aluno.getEmail();
        this.cpf = aluno.getCpf();
        this.matricula = aluno.getMatricula();
        this.formaIngresso = aluno.getFormaIngresso();
        this.turmas = (Set) aluno.getTurmas();
    }

    public static Page<AlunoDto> from(Page<Aluno> alunos) {
        return alunos.map(AlunoDto::new);
    }



}
