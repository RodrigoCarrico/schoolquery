package br.com.onboard.schoolquery.turma.dto;

import br.com.onboard.schoolquery.turma.model.Turma;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class TurmaDto {
    private String id;
    private String descricao;
    private int anoLetivo;
    private int periodoLetivo;
    private int numeroVagas;

    public TurmaDto(Turma turma) {
        this.id = turma.getId();
        this.descricao = turma.getDescricao();
        this.anoLetivo = turma.getAnoLetivo();
        this.periodoLetivo = turma.getPeriodoLetivo();
        this.numeroVagas = turma.getNumeroVagas();
    }

    public static List<TurmaDto> converter(List<Turma> turmas) {
        return turmas.stream().map(TurmaDto::new).collect(Collectors.toList());
    }

}
