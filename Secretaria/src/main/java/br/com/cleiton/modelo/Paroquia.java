package br.com.cleiton.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@javax.persistence.Entity
public class Paroquia extends Entity {

	private String name;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "paroquia")
	private List<Encontro> encontros;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "paroquia")
	private List<Pessoa> pessoas;

	public Paroquia(Long idParoquia) {
		super();
		super.setId(idParoquia);
	}

	public Paroquia() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<Encontro> getEncontros() {
		return encontros;
	}

	public void setEncontros(List<Encontro> encontros) {
		this.encontros = encontros;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}


}
