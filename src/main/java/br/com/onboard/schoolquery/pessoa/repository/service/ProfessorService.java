package br.com.onboard.schoolquery.pessoa.repository.service;

import br.com.onboard.schoolquery.pessoa.amqp.event.ProfessorCriadoEvent;
import br.com.onboard.schoolquery.pessoa.repository.model.ProfessorDisciplina;
import br.com.onboard.schoolquery.pessoa.repository.view.ProfessorView;
import br.com.onboard.schoolquery.pessoa.repository.view.ProfessorViewRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.onboard.schoolquery.pessoa.dto.ProfessorDto;
import br.com.onboard.schoolquery.pessoa.enums.Titulacao;
import br.com.onboard.schoolquery.pessoa.repository.model.Professor;
import br.com.onboard.schoolquery.pessoa.repository.ProfessorRepository;

import java.util.HashSet;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ProfessorService {

	@Autowired
	ProfessorRepository professorRepository;

	@Autowired
	ProfessorViewRepository professorViewRepository;

	public void on(final ProfessorCriadoEvent event) {

		var disciplinas = event.getDisciplinas() != null
				? event.getDisciplinas()
				.stream()
				.map(disciplina -> ProfessorDisciplina.of(disciplina.getDisciplinaId(), event.getId()))
				.collect(Collectors.toSet())
				: new HashSet<ProfessorDisciplina>();

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

		ProfessorView professorView = ProfessorView.builder()
				.cpf(cpf)
				.titulacao(titulacao)
				.nome(nome)
				.email(email)
				.id(id)
				.build();
		
		Example<ProfessorView> example = Example.of(professorView);

		Page<ProfessorView> pageProfessores = professorViewRepository.findAll(example, pageable);

		return ProfessorDto.from(pageProfessores);
	}

}
