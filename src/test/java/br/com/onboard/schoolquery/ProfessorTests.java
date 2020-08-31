package br.com.onboard.schoolquery;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProfessorTests {
	/*@Autowired
	ProfessorService professorService;
	
	
	@Test
	@DisplayName("Teste de conversão de String para Objeto professor")
	public void ConverterStringParaObjetoProfessor() {
		Professor professor = ProfessorDto.toProfessor(
				"{\"titulacao\":\"MESTRE\",\"id\":\"123\",\"nome\":\"teste teste\",\"email\":\"teste@teste.com.br\",\"cpf\":\"00000000000\"}");

		assertThat(professor).isNotNull();
	}
	
	@Test
	@DisplayName("Cadastra novo item na tabela Professores")
	public void CadastraNovoProfessorTeste() {
		String message = "{\"titulacao\":\"MESTRE\",\"id\":\"123\",\"nome\":\"teste teste\",\"email\":\"teste@teste.com.br\",\"cpf\":\"00000000000\"}";
		professorService.salvar(ProfessorDto.toProfessor(message));
		Pageable pageable = PageRequest.of(0, 10, Direction.ASC, "id");
		Page<ProfessorDto> pageProfessoresDto = professorService.getProfessores(pageable, null, null,
				"rodrigo@teste.com.br", null, null);

		assertThat(pageProfessoresDto.getSize()).isGreaterThan(0);
	}

	
	@Test
	@DisplayName("Teste Consulta professores")
	public void ConsultaProfessoresTeste() {
		Pageable pageable = PageRequest.of(0, 10, Direction.ASC, "id");
		Page<ProfessorDto> pageProfessoresDto = professorService.getProfessores(pageable, null, null,
				"rodrigo@teste.com.br", null, null);

		assertThat(pageProfessoresDto.getSize()).isGreaterThan(0);
	}
*/
}
