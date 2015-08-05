package br.com.cleiton.controlador;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.cleiton.components.UsuarioSession;
import br.com.cleiton.components.login.Public;
import br.com.cleiton.modelo.sistema.Usuario;
import br.com.cleiton.repositorio.UsuarioRepository;

@Resource
public class LoginController {
	private final Result result;
	private final UsuarioSession usuarioSession;
	private final UsuarioRepository usuarioRepository;

	public LoginController(Result result, UsuarioSession usuarioSession, UsuarioRepository usuarioRepository) {
		super();
		this.result = result;
		this.usuarioSession = usuarioSession;
		this.usuarioRepository = usuarioRepository;
	}

	@Public
	@Get("/login")
	public void login() {

	}

	@Get("/logout")
	public void logout() {
		usuarioSession.logout();
		result.redirectTo(this).login();
	}

	@Public
	@Post("/autenticar")
	public void autenticar(Usuario usuario) {
		Usuario user = usuarioRepository.findByLogin(usuario.getNomeUsuario());
		if (user != null) {
			usuarioSession.setUser(user);

			result.redirectTo(EncontroController.class).index();
		} else {
			result.include("error", "E-mail ou senha incorreta!").redirectTo(this).login();
		}
	}
}
