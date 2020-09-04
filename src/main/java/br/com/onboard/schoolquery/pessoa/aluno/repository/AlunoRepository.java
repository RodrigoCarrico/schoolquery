package br.com.onboard.schoolquery.pessoa.aluno.repository;

import br.com.onboard.schoolquery.pessoa.aluno.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, String> {
}
