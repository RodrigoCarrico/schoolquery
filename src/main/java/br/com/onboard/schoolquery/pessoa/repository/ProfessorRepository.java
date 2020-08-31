package br.com.onboard.schoolquery.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.onboard.schoolquery.pessoa.repository.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor,String> {

}
