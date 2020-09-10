package br.com.onboard.schoolquery.turma.dto;

import br.com.onboard.schoolquery.disciplina.dto.DisciplinaDto;
import br.com.onboard.schoolquery.pessoa.aluno.dto.AlunoDto;
import br.com.onboard.schoolquery.pessoa.aluno.model.Aluno;
import br.com.onboard.schoolquery.turma.model.Turma;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class TurmaDto {
    private String id;
    private String descricao;
    private Integer anoLetivo;
    private Integer periodoLetivo;
    private Integer numeroVagas;
    private Set<DisciplinaDto> disciplinas;
    private Set<AlunoDto> alunos;

    public TurmaDto(Turma turma) {
        this.id = turma.getId();
        this.descricao = turma.getDescricao();
        this.anoLetivo = turma.getAnoLetivo();
        this.periodoLetivo = turma.getPeriodoLetivo();
        this.numeroVagas = turma.getNumeroVagas();
        this.disciplinas = (Set) turma.getDisciplinas();
        this.alunos = (Set) turma.getAlunos();
    }

    public static Page<TurmaDto> from(Page<Turma> turmas) {
        return turmas.map(TurmaDto::new);
    }
}
