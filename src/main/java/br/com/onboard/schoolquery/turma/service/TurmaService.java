package br.com.onboard.schoolquery.turma.service;

import br.com.onboard.schoolquery.disciplina.repository.DisciplinaRepository;
import br.com.onboard.schoolquery.turma.amqp.event.TurmaCriadaEvent;
import br.com.onboard.schoolquery.turma.model.Turma;
import br.com.onboard.schoolquery.turma.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TurmaService {
    @Autowired
    TurmaRepository turmaRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Transactional
    public void on(TurmaCriadaEvent event) {
        Turma turma = Turma.builder()
                .anoLetivo(event.getAnoLetivo())
                .descricao(event.getDescricao())
                .disciplinas(event.getDisciplinaEvent(disciplinaRepository))
                .numeroVagas(event.getNumeroVagas())
                .id(event.getId())
                .periodoLetivo(event.getPeriodoLetivo()).build();

        turmaRepository.save(turma);
    }
}
