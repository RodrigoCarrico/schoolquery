package br.com.onboard.schoolquery.pessoa.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.onboard.schoolquery.pessoa.dto.ProfessorDto;
import br.com.onboard.schoolquery.pessoa.enums.Titulacao;
import br.com.onboard.schoolquery.pessoa.service.ProfessorService;


@RestController
@RequestMapping(path = ProfessorController.PATH,  produces = { MediaType.APPLICATION_JSON_VALUE })
public class ProfessorController {
	
	public static final String PATH = "/api/v1/professor";
	
	@Autowired
	ProfessorService professorService;
	
	@GetMapping("/find")
	@Cacheable(value = "listaDeProfessores")
	public Page<ProfessorDto> getProfessores(
			@RequestParam(required = false) String id, 
			@RequestParam(required = false) String nome, 
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String cpf,
			@RequestParam(required = false) Titulacao titulacao,
			@PageableDefault(direction = Direction.ASC, sort = "id", page = 0, size = 10) Pageable pageable) {
		
		Page<ProfessorDto> pageProfessoresDto = professorService.getProfessores(pageable,id, nome, email, cpf, titulacao);
		return pageProfessoresDto;
		
	}
}
