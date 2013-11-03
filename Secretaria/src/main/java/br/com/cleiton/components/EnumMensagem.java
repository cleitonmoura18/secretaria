package br.com.cleiton.components;

public enum EnumMensagem {
	OK("Sucesso"), ERRO("Erro");
	private String descricao;

	private EnumMensagem(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
