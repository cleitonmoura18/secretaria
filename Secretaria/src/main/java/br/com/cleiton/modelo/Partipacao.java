package br.com.cleiton.modelo;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@javax.persistence.Entity
public class Partipacao extends Entity {

	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	private PapelNaEquipe papelNaEquipe;
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	private Pessoa pessoa;

	public PapelNaEquipe getPapelNaEquipe() {
		return papelNaEquipe;
	}

	public void setPapelNaEquipe(PapelNaEquipe papelNaEquipe) {
		this.papelNaEquipe = papelNaEquipe;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	

}
