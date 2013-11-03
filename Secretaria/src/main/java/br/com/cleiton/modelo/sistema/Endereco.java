package br.com.cleiton.modelo.sistema;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Endereco {
	private Long idEnreco;
	private String cep;
	private String numero;
	private String complemento;
	private String bairro;
	private String logradouro;
	@ManyToOne(optional = false)
	private Cidade cidade;

	/**
	 * @return the idEnreco
	 */
	public Long getIdEnreco() {
		return idEnreco;
	}

	/**
	 * @param idEnreco
	 *            the idEnreco to set
	 */
	public void setIdEnreco(Long idEnreco) {
		this.idEnreco = idEnreco;
	}

	/**
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * @param cep
	 *            the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * @param complemento
	 *            the complemento to set
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * @param bairro
	 *            the bairro to set
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * @return the logradouro
	 */
	public String getLogradouro() {
		return logradouro;
	}

	/**
	 * @param logradouro
	 *            the logradouro to set
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * @return the cidade
	 */
	public Cidade getCidade() {
		return cidade;
	}

	/**
	 * @param cidade
	 *            the cidade to set
	 */
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
