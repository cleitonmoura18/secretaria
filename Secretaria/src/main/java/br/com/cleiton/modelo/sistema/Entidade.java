package br.com.cleiton.modelo.sistema;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class Entidade {
	private Long idEntidade;
	/**
	 * Em Pessoa Jurídica representa a razão social
	 */
	private String nome;
	private String fone;
	/**
	 * Representa Celular para o contribuinte e fax para PessoaJurídica
	 */
	private String fone2;
	@OneToOne
	private Endereco endereco;
	@ManyToOne
	private Atividade atividadePrincipal;
	@ManyToOne
	private Atividade atividadeSecundaria;

	/**
	 * @return the endereco
	 */
	public Endereco getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco
	 *            the endereco to set
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return the idEntidade
	 */
	public Long getIdEntidade() {
		return idEntidade;
	}

	/**
	 * @param idEntidade
	 *            the idEntidade to set
	 */
	public void setIdEntidade(Long idEntidade) {
		this.idEntidade = idEntidade;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the fone
	 */
	public String getFone() {
		return fone;
	}

	/**
	 * @param fone
	 *            the fone to set
	 */
	public void setFone(String fone) {
		this.fone = fone;
	}

	/**
	 * @return Representa Celular para o contribuinte e fax para PessoaJurídica
	 */
	public String getFone2() {
		return fone2;
	}

	/**
	 * @param fone2
	 *            Representa Celular para o contribuinte e fax para
	 *            PessoaJurídica
	 */
	public void setFone2(String fone2) {
		this.fone2 = fone2;
	}

}
