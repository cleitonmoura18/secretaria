package br.com.cleiton.modelo;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@javax.persistence.Entity
public class PapelNaEquipe extends Entity implements Comparable<PapelNaEquipe> {

	private String nome;
	@ManyToOne(fetch = FetchType.LAZY)
	private Encontro encontro;
	private Integer ordemImpressao;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	public String getNameUpercase() {
		return nome.toUpperCase();
	}

	public Encontro getEncontro() {
		return encontro;
	}

	public void setEncontro(Encontro encontro) {
		this.encontro = encontro;
	}

	public Integer getOrdemImpressao() {
		return ordemImpressao;
	}

	public void setOrdemImpressao(Integer ordemImpressao) {
		this.ordemImpressao = ordemImpressao;
	}

	@Override
	public int compareTo(PapelNaEquipe o) {
		int compareQuantity = (o).getOrdemImpressao();
		// ascending order
		return this.ordemImpressao - compareQuantity;

	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		PapelNaEquipe papelNaEquipe = new PapelNaEquipe();
		papelNaEquipe.setNome(nome);
		papelNaEquipe.setOrdemImpressao(ordemImpressao);
		return papelNaEquipe;
	}

}
