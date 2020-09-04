package br.com.onboard.schoolquery.pessoa.professor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.onboard.schoolquery.pessoa.professor.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor,String> {

}
