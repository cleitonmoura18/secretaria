package br.com.cleiton.modelo.sistema.enumeracao;
/**
 * Representa os niveis escolares
 * @author Cleiton
 *
 */
public enum NivelEscolar {
	;
	private int codigo;
	private String nome;
	
	

	private NivelEscolar(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
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
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

}
