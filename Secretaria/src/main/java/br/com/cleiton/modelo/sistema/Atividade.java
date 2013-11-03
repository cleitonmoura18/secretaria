package br.com.cleiton.modelo.sistema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Atividade {
	@Id
	@GeneratedValue
	private long idAtividade;
	private String nome;
	/**
	 * @return the idAtividade
	 */
	public long getIdAtividade() {
		return idAtividade;
	}
	/**
	 * @param idAtividade the idAtividade to set
	 */
	public void setIdAtividade(long idAtividade) {
		this.idAtividade = idAtividade;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
