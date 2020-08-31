package br.com.onboard.schoolquery.pessoa.repository;

import br.com.onboard.schoolquery.pessoa.repository.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, String> {
}
