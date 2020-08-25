package br.com.onboard.schoolquery.pessoa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EqualsAndHashCode
@NoArgsConstructor
public abstract class Pessoa implements Serializable{

	private static final long serialVersionUID = 2184237278320112029L;
	@Id
	private String id;
	@NotNull @NotEmpty @Length(min=1, max = 255)
	private String nome;
	@NotNull @NotEmpty @Length(min=1, max = 100)
	private String email; 
	@NotNull @NotEmpty @Length(min=1, max = 12)
	private String cpf;
	
	public Pessoa(String id, String nome, String email, String cpf) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
	}

}
