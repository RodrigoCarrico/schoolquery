package br.com.onboard.schoolquery.pessoa.repository;

import br.com.onboard.schoolquery.pessoa.repository.model.ProfessorDisciplina;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ProfessorDisciplinaRepositoryTest {
    @Autowired
    ProfessorDisciplinaRepository professorDisciplinaRepository;

   /* @Test
    @DisplayName("Teste de pesquisa")
    public void Teste() {

        professorDisciplinaRepository.findAll();
        Professor professor = ProfessorDto.toProfessor(
                "{\"titulacao\":\"MESTRE\",\"id\":\"123\",\"nome\":\"teste teste\",\"email\":\"teste@teste.com.br\",\"cpf\":\"00000000000\"}");

        assertThat(professor).isNotNull();
    }*/

}