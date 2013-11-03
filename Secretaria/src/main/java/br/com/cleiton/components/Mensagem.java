package br.com.cleiton.components;

/**
 * Classe criada para retornar as mensagens json
 * 
 * @author Cleiton
 * 
 */
public class Mensagem {
	private EnumMensagem enumMensagem;
	private String mensagem;

	public Mensagem(EnumMensagem enumMensagem, String mensagem) {
		super();
		this.enumMensagem = enumMensagem;
		this.mensagem = mensagem;
	}

	public Mensagem(String mensagem) {
		super();
		this.enumMensagem = EnumMensagem.OK;
		this.mensagem = mensagem;
	}

	public EnumMensagem getEnumMensagem() {
		return enumMensagem;
	}

	public void setEnumMensagem(EnumMensagem enumMensagem) {
		this.enumMensagem = enumMensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	

}
