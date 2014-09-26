package br.com.cleiton.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@javax.persistence.Entity
public class Encontro extends Entity {

	private Date dataInicio;
	private Date dataFim;
	private String tema;
	private String lema;
	@ManyToOne(fetch = FetchType.LAZY)
	private Paroquia paroquia;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "encontro")
	private List<Equipe> equipes;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "encontro")
	private List<PapelNaEquipe> papeisNaEquipe;
	

	public Encontro(Long idEncontro) {
		super();
		super.setId(idEncontro);
	}

	public Encontro() {
		super();
	}



	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public Paroquia getParoquia() {
		return paroquia;
	}

	public void setParoquia(Paroquia paroquia) {
		this.paroquia = paroquia;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getLema() {
		return lema;
	}

	public void setLema(String lema) {
		this.lema = lema;
	}

	public List<PapelNaEquipe> getPapeisNaEquipe() {
		return papeisNaEquipe;
	}

	public void setPapeisNaEquipe(List<PapelNaEquipe> papeisNaEquipe) {
		this.papeisNaEquipe = papeisNaEquipe;
	}
	
	public List<Equipe> getCirculos() {
		ArrayList<Equipe> circulos = new ArrayList<Equipe>();
		for (Equipe equipe : equipes) {
			if(equipe.isCirculo()){
				circulos.add(equipe);
			}
		}
		return circulos;
	}
	

}
