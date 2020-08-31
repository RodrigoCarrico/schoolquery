package br.com.onboard.schoolquery.disciplina.repository;

import br.com.onboard.schoolquery.disciplina.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, String> {
}
