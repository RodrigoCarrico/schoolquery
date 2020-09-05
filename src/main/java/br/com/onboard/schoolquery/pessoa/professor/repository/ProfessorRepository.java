package br.com.onboard.schoolquery.pessoa.professor.repository;

import br.com.onboard.schoolquery.pessoa.professor.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, String> {

}
