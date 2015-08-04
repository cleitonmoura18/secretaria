package br.com.cleiton.components;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.cleiton.modelo.sistema.Usuario;

@Component
@SessionScoped
public class UsuarioSession {
	private Long idParoquia;
	private Long idEncontro;
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
		return user != null;
	}

	public void logout() {
		user = null;
	}
}
