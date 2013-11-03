package br.com.cleiton.modelo;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CollectionOfElements;
@javax.persistence.Entity
public class Pessoa extends Entity {

	private String nome;
	private Date dataNascimento;
	private String email;
	private String endereco;
	private String bairro;
	private String nomeConjugue;
	@Enumerated(EnumType.STRING)
	private TipoPessoa tipoPessoa;
	@CollectionOfElements(fetch=FetchType.LAZY)
	private List<String> telefones;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Paroquia paroquia;
	

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getBairro() {
		return bairro;
	}

	public Paroquia getParoquia() {
		return paroquia;
	}

	public void setParoquia(Paroquia paroquia) {
		this.paroquia = paroquia;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getNomeConjugue() {
		return nomeConjugue;
	}

	public void setNomeConjugue(String nomeConjugue) {
		this.nomeConjugue = nomeConjugue;
	}
	public String getFonesTemplate() {
		StringBuilder builder= new StringBuilder();
		for (Iterator<String> iterator = telefones.iterator(); iterator.hasNext();) {
			String telefone = (String) iterator.next();
			builder.append(telefone);
			if(iterator.hasNext())
				builder.append("/");
		}
		return builder.toString();
	}
}
