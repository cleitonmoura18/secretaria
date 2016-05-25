package br.com.cleiton.modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionOfElements;

import br.com.cleiton.components.Utils;

@javax.persistence.Entity
public class Pessoa extends Entity implements Comparable<Pessoa>{

	private String nome;
	private Date dataNascimento;
	private String email = "";
	private String endereco = "";
	private String bairro = "";
	private String nomeConjugue;
	private String nomeCrachaConjugue;
	private String nomeCracha;
	@ManyToOne(optional = true)
	private Equipe circulo;

	@Enumerated(EnumType.STRING)
	private TipoPessoa tipoPessoa;
	@CollectionOfElements(fetch = FetchType.LAZY)
//	@ElementCollection(fetch = FetchType.LAZY)
	private List<String> telefones;

	@ManyToOne(fetch = FetchType.LAZY)
	private Paroquia paroquia;
	
	@Transient
	private Boolean marcado;

	public Pessoa() {
		super();
		telefones = new ArrayList<String>();
		marcado = Boolean.FALSE;
	}

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

	public String getDataNascimentoFormatado() {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		return fmt.format(dataNascimento);
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
		StringBuilder builder = new StringBuilder();
		for (Iterator<String> iterator = getTelefones().iterator(); iterator
				.hasNext();) {
			String telefone = (String) iterator.next();
			builder.append(formatCelular(telefone));
			if (iterator.hasNext())
				builder.append("/");
		}
		return builder.toString();
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public String getNomeCracha() {
		return nomeCracha;
	}

	public void setNomeCracha(String nomeCracha) {
		this.nomeCracha = nomeCracha;
	}

	public String getNomeCrachaConjugue() {
		return nomeCrachaConjugue;
	}

	public void setNomeCrachaConjugue(String nomeCrachaConjugue) {
		this.nomeCrachaConjugue = nomeCrachaConjugue;
	}

	public Equipe getCirculo() {
		return circulo;
	}

	public void setCirculo(Equipe circulo) {
		this.circulo = circulo;
	}

	public static void main(String[] args) {

		System.out.println(formatCelular("93227-0387"));
		System.out.println(formatCelular("3227-0387"));
		System.out.println(formatCelular("(86)3227-0387"));
	}

	public static String formatCelular(String celular) {
		celular = celular.replaceAll("[^\\d.]", "");
		if (celular.length() == 8 || celular.length() == 9)
			celular = "86" + celular;
		if (celular.length() == 11) {
			return Utils.format("(##)#####-####", celular);
		}
		if (celular.length() == 10)
			return Utils.format("(##)####-####", celular);
		return celular;
	}

	public Boolean getMarcado() {
		return marcado;
	}

	public void setMarcado(Boolean marcado) {
		this.marcado = marcado;
	}
	public String getNomeFormatado(){
		if(this.getTipoPessoa().equals(TipoPessoa.CASAL)) {
			return  Utils.padronizarNomes(this.getNome() + " e Tia "+ this.getNomeConjugue());
		} else {
			return Utils.padronizarNomes(this.getNome());
		}
	}

	@Override
	public int compareTo(Pessoa o) {
		String nome = (o).getNome();
		// ascending order
		return this.nome.compareTo( nome);
	}
	
}
