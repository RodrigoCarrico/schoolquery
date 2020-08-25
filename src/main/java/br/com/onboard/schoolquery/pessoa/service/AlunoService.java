package br.com.onboard.schoolquery.pessoa.service;

import br.com.onboard.schoolquery.pessoa.dto.AlunoDto;
import br.com.onboard.schoolquery.pessoa.enums.FormaIngresso;
import br.com.onboard.schoolquery.pessoa.model.Aluno;
import br.com.onboard.schoolquery.pessoa.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlunoService {
    @Autowired
    AlunoRepository alunoRepository;

    public Page<AlunoDto> getAlunos(
            Pageable pageable,
            String id,
            String nome,
            String email,
            String cpf,
            Integer matricula,
            FormaIngresso formaIngresso) {

        Aluno aluno = Aluno.builder()
                .cpf(cpf).email(email)
                .formaIngresso(formaIngresso).id(id)
                .matricula(matricula).nome(nome).build();

        Example<Aluno> example = Example.of(aluno);

        Page<Aluno> pageAlunos = alunoRepository.findAll(example, pageable);

        return AlunoDto.from(pageAlunos);
    }

    @Transactional
    public void salvar(Aluno aluno) {
        alunoRepository.save(aluno);
        System.out.println("Gravou no BD Aluno: " + aluno.toString());
    }

}
