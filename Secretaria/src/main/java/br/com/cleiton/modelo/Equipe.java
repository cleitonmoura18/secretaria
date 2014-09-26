package br.com.cleiton.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@javax.persistence.Entity
public class Equipe extends Entity implements Comparable<Equipe> {

	private String name;
	private Integer ordemImpressao;
	@ManyToOne(fetch = FetchType.LAZY)
	private Encontro encontro;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy="equipe")
	private List<Participacao> partipacao;
	
	private boolean precisaPapelNaEquipe;
	private boolean circulo;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Encontro getEncontro() {
		return encontro;
	}

	public void setEncontro(Encontro encontro) {
		this.encontro = encontro;
	}

	public List<Participacao> getPartipacao() {
		return partipacao;
	}

	public List<Participacao> getPartipacaoPorPapel(PapelNaEquipe papelNaEquipe) {
		List<Participacao> participacaos = new ArrayList<Participacao>();
		for (Participacao participacao : this.partipacao) {
			if (participacao.getPapelNaEquipe().equals(papelNaEquipe))
				participacaos.add(participacao);
		}
		return participacaos;
	}

	public void setPartipacao(List<Participacao> partipacao) {
		this.partipacao = partipacao;
	}

	public Integer getOrdemImpressao() {
		return ordemImpressao;
	}

	public void setOrdemImpressao(Integer ordemImpressao) {
		this.ordemImpressao = ordemImpressao;
	}

	@Override
	public int compareTo(Equipe o) {
		int compareQuantity = (o).getOrdemImpressao();
		// ascending order
		return this.ordemImpressao - compareQuantity;

	}

	@Override
	public String toString() {
		return "Equipe [name=" + name + ", ordemImpressao=" + ordemImpressao
				+ ", encontro=" + encontro + ", partipacao=" + partipacao + "]";
	}

	public boolean isPrecisaPapelNaEquipe() {
		return precisaPapelNaEquipe;
	}

	public void setPrecisaPapelNaEquipe(boolean precisaPapelNaEquipe) {
		this.precisaPapelNaEquipe = precisaPapelNaEquipe;
	}

	public boolean isCirculo() {
		return circulo;
	}

	public void setCirculo(boolean circulo) {
		this.circulo = circulo;
	}

	
	
}
