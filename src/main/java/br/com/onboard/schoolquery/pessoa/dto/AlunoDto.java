package br.com.onboard.schoolquery.pessoa.dto;

import br.com.onboard.schoolquery.pessoa.enums.FormaIngresso;
import br.com.onboard.schoolquery.pessoa.repository.model.Aluno;
import br.com.onboard.schoolquery.turma.dto.TurmaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

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

    private List<TurmaDto> turmas = new ArrayList<>();

    public AlunoDto(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.email = aluno.getEmail();
        this.cpf = aluno.getCpf();
        this.matricula = aluno.getMatricula();
        this.formaIngresso = aluno.getFormaIngresso();
        this.setTurmas(TurmaDto.converter(aluno.getTurmas()));
    }

   /* public Aluno converter() {
        return Aluno.builder()
                .id(this.id).cpf(this.cpf)
                .email(this.email).matricula(this.matricula)
                .formaIngresso(this.formaIngresso).build();

    }*/


    public static Page<AlunoDto> from(Page<Aluno> alunos) {
        return alunos.map(AlunoDto::new);
    }



}
