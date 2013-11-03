package br.com.cleiton.modelo.sistema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 * Representa os Estados da federação Brasileira
 * @author Cleiton
 *
 */
@Entity
public class Estado {
	@Id
	@GeneratedValue
	private Long idEstado;
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
