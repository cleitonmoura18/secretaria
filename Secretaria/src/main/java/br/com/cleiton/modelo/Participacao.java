package br.com.cleiton.modelo;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@javax.persistence.Entity
public class Participacao extends Entity {

	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	private PapelNaEquipe papelNaEquipe;
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	private Pessoa pessoa;
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	private Equipe equipe;

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

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
