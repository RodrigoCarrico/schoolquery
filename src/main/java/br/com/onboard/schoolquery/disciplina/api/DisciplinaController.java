package br.com.onboard.schoolquery.disciplina.api;

import br.com.onboard.schoolquery.disciplina.dto.DisciplinaDto;
import br.com.onboard.schoolquery.disciplina.model.Disciplina;
import br.com.onboard.schoolquery.disciplina.repository.DisciplinaRepository;
import br.com.onboard.schoolquery.disciplina.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = DisciplinaController.PATH)
public class DisciplinaController {

    public static final String PATH = "/api/v1/disciplina";

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @GetMapping("/find")
    public Page<DisciplinaDto> getDisciplina(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String descricao,
            @RequestParam(required = false) String sigla,
            @RequestParam(required = false) String professorId,
            @PageableDefault(direction = Sort.Direction.ASC, sort = "id", page = 0, size = 10) Pageable pageable) {

        return disciplinaService.getDisciplina(pageable, id, descricao, sigla, professorId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaDto> getById(@PathVariable String id) {
        Optional<Disciplina> optional = disciplinaRepository.findById(id);

        if (optional.isPresent()) {
            return ResponseEntity.ok(new DisciplinaDto(optional.get()));
        }

        return ResponseEntity.notFound().build();
    }
}
