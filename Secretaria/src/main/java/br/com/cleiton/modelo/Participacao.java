package br.com.cleiton.modelo;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@javax.persistence.Entity
public class Participacao extends Entity implements Comparable<Participacao>{

	@ManyToOne(fetch=FetchType.LAZY,optional=true)
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

	public int compareTo(Participacao o) {
		int compareQuantity = (o).getPapelNaEquipe().getOrdemImpressao();
		// ascending order
		return this.getPapelNaEquipe().getOrdemImpressao() - compareQuantity;
	}

}
