package br.com.onboard.schoolquery.pessoa.aluno.api;

import br.com.onboard.schoolquery.pessoa.aluno.dto.AlunoDto;
import br.com.onboard.schoolquery.pessoa.enums.FormaIngresso;
import br.com.onboard.schoolquery.pessoa.aluno.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = AlunoController.PATH, produces = {MediaType.APPLICATION_JSON_VALUE})
public class AlunoController {

    public static final String PATH = "/api/v1/aluno";

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/find")
    @Cacheable(value = "listaDeAlunos")
    public Page<AlunoDto> getAlunos(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) Integer matricula,
            @RequestParam(required = false) FormaIngresso formaIngresso,
            @PageableDefault(direction = Sort.Direction.ASC, sort = "id", page = 0, size = 10) Pageable pageable) {

        return  alunoService.getAlunos(pageable, id, nome, email, cpf, matricula, formaIngresso);
    }
}
