package br.com.onboard.schoolquery.pessoa.aluno.service;

import br.com.onboard.schoolquery.pessoa.aluno.amqp.event.AlunoAlteradoEvent;
import br.com.onboard.schoolquery.pessoa.aluno.amqp.event.AlunoCriadoEvent;
import br.com.onboard.schoolquery.pessoa.aluno.dto.AlunoDto;
import br.com.onboard.schoolquery.pessoa.aluno.model.Aluno;
import br.com.onboard.schoolquery.pessoa.aluno.repository.AlunoRepository;
import br.com.onboard.schoolquery.pessoa.enums.FormaIngresso;
import br.com.onboard.schoolquery.turma.repository.TurmaRepository;
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

    @Autowired
    TurmaRepository turmaRepository;

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
    public void on(AlunoCriadoEvent event) {
        Aluno aluno = Aluno.builder().cpf(event.getCpf())
                .email(event.getEmail())
                .nome(event.getNome())
                .id(event.getId())
                .matricula(event.getMatricula())
                .formaIngresso(event.getFormaIngresso())
                .turmas(event.getTurmasEvent(turmaRepository))
                .build();

        alunoRepository.save(aluno);
    }

    @Transactional
    public void on(AlunoAlteradoEvent event) {
        Aluno aluno = Aluno.builder().cpf(event.getCpf())
                .email(event.getEmail())
                .nome(event.getNome())
                .id(event.getId())
                .matricula(event.getMatricula())
                .formaIngresso(event.getFormaIngresso())
                .turmas(event.getTurmasEvent(turmaRepository))
                .build();

        alunoRepository.save(aluno);
        System.out.println("Gravou no BD Aluno: " + aluno.toString());
    }

}
