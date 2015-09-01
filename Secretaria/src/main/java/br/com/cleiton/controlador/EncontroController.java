package br.com.cleiton.controlador;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import br.com.cleiton.components.UsuarioSession;
import br.com.cleiton.modelo.Encontro;
import br.com.cleiton.modelo.Equipe;
import br.com.cleiton.modelo.Paroquia;
import br.com.cleiton.repositorio.EncontroRepository;
import br.com.cleiton.repositorio.EquipeRepository;
import br.com.cleiton.repositorio.ParoquiaRepository;
import br.com.cleiton.word.ArquivoWord;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.download.Download;

@Resource
public class EncontroController {

	private final Result result;
	private final EncontroRepository repository;
	private final EquipeRepository equipeRepository;
	private final ParoquiaRepository paroquiaRepository;
	private final UsuarioSession session;

	private final Validator validator;

	public EncontroController(Result result, EncontroRepository repository,
			EquipeRepository equipeRepository,
			ParoquiaRepository paroquiaRepository, Validator validator,
			UsuarioSession session) {
		this.result = result;
		this.equipeRepository = equipeRepository;
		this.repository = repository;
		this.paroquiaRepository = paroquiaRepository;
		this.validator = validator;
		this.session = session;
	}

	@Get("criarQuadrante")
	public Download criarQuadrante() throws URISyntaxException {
		ArquivoWord arquivoWord = new ArquivoWord();
		try {
			return arquivoWord.criarQuadrante(repository.find(session
					.getIdEncontro()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Get("criarQuadrante/equipe/{idEquipe}")
	public Download criarEquipe(Long idEquipe) throws URISyntaxException {
		ArquivoWord arquivoWord = new ArquivoWord();
		try {
			Equipe equipe = equipeRepository.find(idEquipe);
			return arquivoWord.criarEquipe(equipe.getEncontro(), equipe);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Get("/encontros")
	public List<Encontro> index() {
		return repository.findAll();
	}

	@Post("/encontros")
	public void create(Encontro encontro) {
		validator.validate(encontro);
		validator.onErrorUsePageOf(this).newEncontro();
		encontro.setParoquia(paroquiaRepository.find(session.getIdParoquia()));
		repository.create(encontro);
		result.redirectTo(ParoquiaController.class).listEncontros(
				encontro.getParoquia().getId());
	}

	@Get("/encontros/new")
	public Encontro newEncontro() {
		Encontro encontro = new Encontro();
		encontro.setParoquia(new Paroquia());
		encontro.getParoquia().setId(session.getIdParoquia());
		return encontro;
	}

	@Put("/encontros")
	public void update(Encontro encontro) {
		validator.validate(encontro);
		validator.onErrorUsePageOf(this).edit(encontro);
		encontro.setParoquia(paroquiaRepository.find(session.getIdParoquia()));
		repository.update(encontro);
		result.redirectTo(ParoquiaController.class).listEncontros(
				session.getIdParoquia());
	}

	@Get("/encontros/{encontro.id}/edit")
	public Encontro edit(Encontro encontro) {
		return repository.find(encontro.getId());
	}

	@Get("/encontros/{encontro.id}")
	public Encontro show(Encontro encontro) {
		session.setIdEncontro(encontro.getId());
		Encontro encontroView = repository.find(encontro.getId());
		Collections.sort(encontroView.getPapeisNaEquipe());
		Collections.sort(encontroView.getEquipes());
		return encontroView;
	}

	@Delete("/encontros/{encontro.id}")
	public void destroy(Encontro encontro) {
		repository.destroy(repository.find(encontro.getId()));
		result.redirectTo(this).index();
	}

	public static void main(String[] args) {

	}
}