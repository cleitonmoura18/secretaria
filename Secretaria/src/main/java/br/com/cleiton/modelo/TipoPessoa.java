package br.com.cleiton.modelo;

public enum TipoPessoa {
	PADRE("Padre","Data de Ordenação" ), JOVEM("Jovem","Data de Nascimento"), CASAL("Casal","Data de Casamento");
	private String descricao;
	private String data;

	

	private TipoPessoa(String descricao, String data) {
		this.descricao = descricao;
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
