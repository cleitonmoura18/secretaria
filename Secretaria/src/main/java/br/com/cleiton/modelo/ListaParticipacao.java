package br.com.cleiton.modelo;

import java.util.List;

public class ListaParticipacao {
	private Equipe equipe;
	private PapelNaEquipe papelNaEquipe;
	private List<Pessoa> pessoas;

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

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}
