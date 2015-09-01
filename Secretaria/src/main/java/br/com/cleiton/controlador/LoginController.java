package br.com.cleiton.controlador;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.cleiton.components.CriptoUtils;
import br.com.cleiton.components.UsuarioSession;
import br.com.cleiton.components.login.Public;
import br.com.cleiton.modelo.sistema.Usuario;
import br.com.cleiton.repositorio.UsuarioRepository;

@Resource
public class LoginController {
	private final Result result;
	private final UsuarioSession usuarioSession;
	private final UsuarioRepository repository;

	private final Validator validator;

	public LoginController(Result result, UsuarioSession usuarioSession,
			UsuarioRepository repository, Validator validator) {
		super();
		this.result = result;
		this.usuarioSession = usuarioSession;
		this.repository = repository;
		this.validator = validator;
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
	public void autenticar(Usuario usuario) throws NoSuchAlgorithmException {
		
		Usuario user = repository.findByLogin(usuario.getNomeUsuario());
		String senhaCriptografada = CriptoUtils.criptografa(usuario.getSenha());
		if (user != null && user.getSenha().equals(senhaCriptografada)) {
			
			usuarioSession.setUser(user);
			result.redirectTo(ParoquiaController.class).index();
		} else {
			validator.add(new ValidationMessage("Usuário ou senha não conferem", "senhaErrada"));
			 validator.onErrorUsePageOf(LoginController.class)
		      .login();
		}
	}

	@Get("/usuario/new")
	public Usuario newUsuario() {
		return new Usuario();
	}

	@Put("/usuarios")
	public void update(Usuario usuario) throws NoSuchAlgorithmException {
		validator.validate(usuario);
		validator.onErrorUsePageOf(this).edit(usuario);
		verificarSenhasIguias(usuario);
		repository.update(usuario);
		result.redirectTo(ParoquiaController.class).index();
	}

	@Get("/usuarios/{usuario.idUsuario}/edit")
	public Usuario edit(Usuario usuario) {

		Usuario find = repository.find(usuario.getIdUsuario());
		if (find != null) {
			find.setSenha(null);
		}
		return find;
	}

	@Post("/usuarios")
	public void create(Usuario usuario) throws NoSuchAlgorithmException {
		validator.validate(usuario);
		verificarSenhasIguias(usuario);
		validator.onErrorUsePageOf(this).newUsuario();
		repository.create(usuario);
		result.redirectTo(this).show(usuario);
	}

	@Get("/usuarios/{usuario.idUsuario}")
	public Usuario show(Usuario usuario) {
		return repository.find(usuario.getIdUsuario());
	}

	@Public
	@Get("/usuarios/criarUsuarioCleiton")
	public void show() throws NoSuchAlgorithmException {
		Usuario usuario = new Usuario();
		usuario.setEmail("cleitonmoura18@gmail.com");
		usuario.setNomeUsuario("cleiton");
		usuario.setSenha(CriptoUtils.criptografa("java"));
		List<Usuario> findAll = repository.findAll();
		System.out.println(findAll.size());
		for (Usuario usuario2 : findAll) {
			System.out.println(usuario2.getNomeUsuario());
		}
		if (findAll.size() == 0) {
			repository.create(usuario);
		}
	}

	public void verificarSenhasIguias(Usuario usuario)
			throws NoSuchAlgorithmException {
		if (!usuario.getSenha().equals(usuario.getSenhaRepetida())) {
			validator.add(new ValidationMessage(
					"As senhas informadas não estão iguais", "produto.nome"));
		}
		String senhaCriptografada = CriptoUtils.criptografa(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
	}
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(CriptoUtils.criptografa("java"));
	}
}
