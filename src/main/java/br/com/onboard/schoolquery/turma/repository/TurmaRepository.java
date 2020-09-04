package br.com.onboard.schoolquery.turma.repository;

import br.com.onboard.schoolquery.turma.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, String> {
}
