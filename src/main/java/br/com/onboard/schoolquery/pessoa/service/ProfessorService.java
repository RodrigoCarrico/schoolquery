package br.com.onboard.schoolquery.pessoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.onboard.schoolquery.pessoa.dto.ProfessorDto;
import br.com.onboard.schoolquery.pessoa.enums.Titulacao;
import br.com.onboard.schoolquery.pessoa.model.Professor;
import br.com.onboard.schoolquery.pessoa.repository.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	ProfessorRepository professorRepository;

	public Page<ProfessorDto> getProfessores(
			Pageable pageable, 
			String id, 
			String nome, 
			String email, 
			String cpf,
			Titulacao titulacao) {
		
		Professor professor = new Professor();
		professor.setCpf(cpf);
		professor.setEmail(email);
		professor.setId(id);
		professor.setNome(nome);
		professor.setTitulacao(titulacao);
		
		Example<Professor> example = Example.of(professor);	

		Page<Professor> pageProfessores = professorRepository.findAll(example, pageable);

		return ProfessorDto.from(pageProfessores);
	}

	@Transactional
	public void salvar(Professor professor) {
		professorRepository.save(professor);
		System.out.println("Gravou no BD Professor: " + professor.toString());
	}

}
