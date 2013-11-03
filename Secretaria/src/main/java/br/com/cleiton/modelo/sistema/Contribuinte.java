package br.com.cleiton.modelo.sistema;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.cleiton.modelo.sistema.enumeracao.NivelEscolar;

public class Contribuinte extends Entidade {
	private String cpf;
	private String email;
	@Enumerated(EnumType.STRING)
	private NivelEscolar nivelEscolar;

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf
	 *            the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the nivelEscolar
	 */
	public NivelEscolar getNivelEscolar() {
		return nivelEscolar;
	}

	/**
	 * @param nivelEscolar
	 *            the nivelEscolar to set
	 */
	public void setNivelEscolar(NivelEscolar nivelEscolar) {
		this.nivelEscolar = nivelEscolar;
	}

}
