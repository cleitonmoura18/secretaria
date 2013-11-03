package br.com.cleiton.modelo.sistema;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
/**
 * Representa as Cidades Brasileiras
 * @author Cleiton
 *
 */
@Entity
public class Cidade {
	@Id
	@GeneratedValue
	private Long idCidade;
	private String nome;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Estado estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
