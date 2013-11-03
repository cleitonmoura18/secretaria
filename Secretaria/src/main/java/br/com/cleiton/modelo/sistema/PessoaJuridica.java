package br.com.cleiton.modelo.sistema;

import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.cleiton.modelo.sistema.enumeracao.PorteEmpresa;

public class PessoaJuridica {
	
	@ManyToOne(fetch=FetchType.LAZY)
	private List<Entidade> socios;
	private boolean isIsentoIssqn;
	private String cnpj;
	private String nomeFantasia;
	@Temporal(TemporalType.DATE)
	private Date inicioAtividade;
	@Temporal(TemporalType.DATE)
	private Date fimAtividade;
	/**
	 * Indica o tamanho da Empresa
	 */
	@Enumerated(EnumType.STRING)
	private PorteEmpresa porteEmpresa;
	/**
	 * @return the isIsentoIssqn
	 */
	public boolean isIsentoIssqn() {
		return isIsentoIssqn;
	}
	/**
	 * @param isIsentoIssqn the isIsentoIssqn to set
	 */
	public void setIsentoIssqn(boolean isIsentoIssqn) {
		this.isIsentoIssqn = isIsentoIssqn;
	}
	/**
	 * @return the cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}
	/**
	 * @param cnpj the cnpj to set
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	/**
	 * @return the nomeFantasia
	 */
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	/**
	 * @param nomeFantasia the nomeFantasia to set
	 */
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	/**
	 * @return the inicioAtividade
	 */
	public Date getInicioAtividade() {
		return inicioAtividade;
	}
	/**
	 * @param inicioAtividade the inicioAtividade to set
	 */
	public void setInicioAtividade(Date inicioAtividade) {
		this.inicioAtividade = inicioAtividade;
	}
	/**
	 * @return the fimAtividade
	 */
	public Date getFimAtividade() {
		return fimAtividade;
	}
	/**
	 * @param fimAtividade the fimAtividade to set
	 */
	public void setFimAtividade(Date fimAtividade) {
		this.fimAtividade = fimAtividade;
	}
	public PorteEmpresa getPorteEmpresa() {
		return porteEmpresa;
	}
	public void setPorteEmpresa(PorteEmpresa porteEmpresa) {
		this.porteEmpresa = porteEmpresa;
	}
	public List<Entidade> getSocios() {
		return socios;
	}
	public void setSocios(List<Entidade> socios) {
		this.socios = socios;
	}
	
}
