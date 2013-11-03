package br.com.cleiton.modelo.sistema.enumeracao;

/**
 * Representa ostamanhos das Empresas.
 * 
 * @author Cleiton
 * 
 */
public enum PorteEmpresa {
	;
	private int codigo;
	private String descricao;
	

	private PorteEmpresa(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
