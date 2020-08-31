package br.com.onboard.schoolquery.pessoa.repository.service;

import br.com.onboard.schoolquery.disciplina.model.Disciplina;
import br.com.onboard.schoolquery.disciplina.repository.DisciplinaRepository;
import br.com.onboard.schoolquery.pessoa.amqp.event.ProfessorAlteradoEvent;
import br.com.onboard.schoolquery.pessoa.amqp.event.ProfessorCriadoEvent;
import br.com.onboard.schoolquery.pessoa.dto.ProfessorDto;
import br.com.onboard.schoolquery.pessoa.enums.Titulacao;
import br.com.onboard.schoolquery.pessoa.repository.ProfessorRepository;
import br.com.onboard.schoolquery.pessoa.repository.model.Professor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Transactional
    public void on(final ProfessorAlteradoEvent event) {

        Optional<Professor> optional = professorRepository.findById(event.getId());
        optional.ifPresent(professor -> {
            professor.setCpf(event.getCpf());
            professor.setEmail(event.getEmail());
            professor.setNome(event.getNome());
            professor.setTitulacao(event.getTitulacao());
            professorRepository.flush();
        });
    }

    @Transactional
    public void on(final ProfessorCriadoEvent event) {

        var disciplinas = event.getDisciplinas() != null
                ? event.getDisciplinas()
                .stream()
                .map(disciplina -> {
                    Disciplina disciplinaEvent = disciplinaRepository.findById(disciplina.getDisciplinaId()).get();
                    return disciplinaEvent;
                })
                .collect(Collectors.toSet())
                : new HashSet<Disciplina>();

        var professor = Professor.builder()
                .id(event.getId())
                .cpf(event.getCpf())
                .email(event.getEmail())
                .nome(event.getNome())
                .titulacao(event.getTitulacao())
                .disciplinas(disciplinas)
                .build();
        professorRepository.save(professor);
    }

    public Page<ProfessorDto> getProfessores(
            Pageable pageable,
            String id,
            String nome,
            String email,
            String cpf,
            Titulacao titulacao) {

        Professor professorView = Professor.builder()
                .cpf(cpf)
                .titulacao(titulacao)
                .nome(nome)
                .email(email)
                .id(id)
                .build();

        Example<Professor> example = Example.of(professorView);

        Page<Professor> pageProfessores = professorRepository.findAll(example, pageable);

        return ProfessorDto.from(pageProfessores);
    }

}
