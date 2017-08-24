package br.com.cleiton.components;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.cleiton.modelo.sistema.Usuario;

@Component
@SessionScoped
public class UsuarioSession {
	private Long idParoquia;
	private Long idEncontro;
	private String nomeEncontro;
	private Usuario user;

	public Long getIdParoquia() {
		return idParoquia;
	}

	public void setIdParoquia(Long idParoquia) {
		this.idParoquia = idParoquia;
	}

	public Long getIdEncontro() {
		return idEncontro;
	}

	public void setIdEncontro(Long idEncontro) {
		this.idEncontro = idEncontro;
	}

	public boolean isLogged() {
		return getUser() != null;
	}

	public void logout() {
		setUser(null);
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getNomeEncontro() {
		return nomeEncontro;
	}

	public void setNomeEncontro(String nomeEncontro) {
		this.nomeEncontro = nomeEncontro;
	}
}
