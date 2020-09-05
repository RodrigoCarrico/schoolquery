package br.com.onboard.schoolquery.disciplina.service;

import br.com.onboard.schoolquery.disciplina.amqp.event.DisciplinaCriadaEvent;
import br.com.onboard.schoolquery.disciplina.dto.DisciplinaDto;
import br.com.onboard.schoolquery.disciplina.model.Disciplina;
import br.com.onboard.schoolquery.disciplina.repository.DisciplinaRepository;
import br.com.onboard.schoolquery.pessoa.aluno.dto.AlunoDto;
import br.com.onboard.schoolquery.pessoa.aluno.model.Aluno;
import br.com.onboard.schoolquery.pessoa.enums.FormaIngresso;
import br.com.onboard.schoolquery.pessoa.professor.model.Professor;
import br.com.onboard.schoolquery.pessoa.professor.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DisciplinaService {
    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Transactional
    public void on(DisciplinaCriadaEvent event){
        Disciplina disciplina = Disciplina.builder()
                .cargaHoraria(event.getCargaHoraria())
                .descricao(event.getDescricao())
                .id(event.getId())
                .sigla(event.getSigla())
                .professor(event.from(professorRepository))
                .build();

        disciplinaRepository.save(disciplina);
    }

    public Page<DisciplinaDto> getDisciplina(Pageable pageable, String id, String descricao, String sigla, String professorId) {
        Professor professor =null;
        if(professorId!=null && professorId.length()>0){
            Optional<Professor> optional = professorRepository.findById(professorId);
            if(optional.isPresent()){
                professor = optional.get();
            }
        }

        Disciplina disciplina = Disciplina.builder()
                .id(id).descricao(descricao)
                .sigla(sigla).professor(professor).build();

        Example<Disciplina> example = Example.of(disciplina);

        Page<Disciplina> pageDisciplinas = disciplinaRepository.findAll(example, pageable);

        return Disciplina.from(pageDisciplinas);
    }
}
