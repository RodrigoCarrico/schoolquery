package br.com.onboard.schoolquery.pessoa.repository.model;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class ProfessorDisciplinaId implements Serializable {

    private static final long serialVersionUID = 1683037762943176661L;

    private String disciplinaId;

    private String professorId;
}
