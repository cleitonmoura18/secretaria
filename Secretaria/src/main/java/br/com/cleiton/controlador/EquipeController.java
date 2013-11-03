package br.com.cleiton.controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;
import br.com.cleiton.components.Mensagem;
import br.com.cleiton.components.UsuarioSession;
import br.com.cleiton.modelo.Encontro;
import br.com.cleiton.modelo.Equipe;
import br.com.cleiton.repositorio.EncontroRepository;
import br.com.cleiton.repositorio.EquipeRepository;

@Resource
public class EquipeController {

	private final Result result;
	private final EquipeRepository repository;
	private final EncontroRepository encontroRepositorio;
	private final UsuarioSession session;

	private final Validator validator;

	public EquipeController(Result result, EquipeRepository repository,
			EncontroRepository encontroRepositorio, UsuarioSession session,
			Validator validator) {
		super();
		this.result = result;
		this.repository = repository;
		this.encontroRepositorio = encontroRepositorio;
		this.session = session;
		this.validator = validator;
	}

	@Get("/equipes")
	public Encontro index() {

		return encontroRepositorio.find(session.getIdEncontro());
	}

	@Post("/equipes")
	public void create(Equipe equipe) {
		validator.validate(equipe);
		validator.onErrorUsePageOf(this).newEquipe();
		Encontro encontro = encontroRepositorio.find(session.getIdEncontro());
		equipe.setEncontro(encontro);
		equipe.setOrdemImpressao(encontro.getEquipes().size());
		repository.create(equipe);
		result.redirectTo(this).index();
	}

	@Get("/equipes/ordenar")
	public Encontro ordenar() {
		// TODO criar um ordenador pelo para passar objetos ordenado pelo
		// atributo ordemImpressao
		Encontro encontro = encontroRepositorio.find(session.getIdEncontro());
		if (encontro.getEquipes() == null || encontro.getEquipes().isEmpty())
			validator.add(new ValidationMessage("Adicione Equipes ao Encontro",
					""));
		validator.onErrorRedirectTo(EncontroController.class).show(encontro);
		Collections.sort(encontro.getEquipes());
		return encontro;
	}
	
	@Post("/equipes/salvar/ordem")
	public void salvarOrdenacao(String ids) {
		List<String> idsArray = new ArrayList<String>(Arrays.asList(ids.split(",")));
		Encontro encontro = encontroRepositorio.find(session.getIdEncontro());
		List<Equipe> equipes = encontro.getEquipes();

		for (Equipe equipe : equipes) {
			equipe.setOrdemImpressao(idsArray.indexOf(equipe.getId().toString()));
		}
		result.use(Results.json()).from(new Mensagem("Ordenação Salva com Sucesso")).serialize();
	}

	@Get("/equipes/new")
	public Equipe newEquipe() {
		return new Equipe();
	}

	@Put("/equipes")
	public void update(Equipe equipe) {
		validator.validate(equipe);
		validator.onErrorUsePageOf(this).edit(equipe);
		equipe.setEncontro(encontroRepositorio.find(session.getIdEncontro()));
		repository.update(equipe);
		result.redirectTo(this).index();
	}

	@Get("/equipes/{equipe.id}/edit")
	public Equipe edit(Equipe equipe) {

		return repository.find(equipe.getId());
	}

	@Get("/equipes/{equipe.id}")
	public Equipe show(Equipe equipe) {
		return repository.find(equipe.getId());
	}

	@Delete("/equipes/{equipe.id}")
	public void destroy(Equipe equipe) {
		repository.destroy(repository.find(equipe.getId()));
		result.redirectTo(this).index();
	}
	
}