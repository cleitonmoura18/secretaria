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
import br.com.cleiton.modelo.PapelNaEquipe;
import br.com.cleiton.repositorio.EncontroRepository;
import br.com.cleiton.repositorio.PapelNaEquipeRepository;

@Resource
public class PapelNaEquipeController {

	private final Result result;
	private final PapelNaEquipeRepository repository;
	private final EncontroRepository encontroRepositorio;
	private final UsuarioSession session;
	
	private final Validator validator;
	
	public PapelNaEquipeController(Result result,
			PapelNaEquipeRepository repository, EncontroRepository eRepository,
			UsuarioSession session, Validator validator) {
		super();
		this.result = result;
		this.repository = repository;
		this.encontroRepositorio = eRepository;
		this.session = session;
		this.validator = validator;
	}

	@Get("/papelNaEquipes")
	public List<PapelNaEquipe> index() {
		return encontroRepositorio.find(session.getIdEncontro()).getPapeisNaEquipe();
	}
	
	@Post("/papelNaEquipes")
	public void create(PapelNaEquipe papelNaEquipe) {
		validator.validate(papelNaEquipe);
		validator.onErrorUsePageOf(this).newPapelNaEquipe();
		Encontro encontro = encontroRepositorio.find(session.getIdEncontro());
		papelNaEquipe.setEncontro(encontro);
		papelNaEquipe.setOrdemImpressao(encontro.getPapeisNaEquipe().size());
		repository.create(papelNaEquipe);
		result.redirectTo(this).index();
	}
	
	@Get("/papelNaEquipes/new")
	public PapelNaEquipe newPapelNaEquipe() {
		return new PapelNaEquipe();
	}
	
	@Put("/papelNaEquipes")
	public void update(PapelNaEquipe papelNaEquipe) {
		validator.validate(papelNaEquipe);
		validator.onErrorUsePageOf(this).edit(papelNaEquipe);
		papelNaEquipe.setEncontro(new Encontro(session.getIdEncontro()));
		repository.update(papelNaEquipe);
		result.redirectTo(this).index();
	}
	
	@Get("/papelNaEquipes/{papelNaEquipe.id}/edit")
	public PapelNaEquipe edit(PapelNaEquipe papelNaEquipe) {
		
		return repository.find(papelNaEquipe.getId());
	}

	@Get("/papelNaEquipes/{papelNaEquipe.id}")
	public PapelNaEquipe show(PapelNaEquipe papelNaEquipe) {
		return repository.find(papelNaEquipe.getId());
	}

	@Delete("/papelNaEquipes/{papelNaEquipe.id}")
	public void destroy(PapelNaEquipe papelNaEquipe) {
		repository.destroy(repository.find(papelNaEquipe.getId()));
		result.redirectTo(this).index();  
	}
	@Get("/papelNaEquipes/ordenar")
	public Encontro ordenar() {
		Encontro encontro = encontroRepositorio.find(session.getIdEncontro());
		if (encontro.getPapeisNaEquipe() == null || encontro.getPapeisNaEquipe().isEmpty())
			validator.add(new ValidationMessage("Adicione Papeis de Equipe ao Encontro",
					""));
		validator.onErrorRedirectTo(PapelNaEquipeController.class).index();
		Collections.sort(encontro.getPapeisNaEquipe());
		return encontro;
	}
	
	@Post("/papelNaEquipes/salvar/ordem")
	public void salvarOrdenacao(String ids) {
		List<String> idsArray = new ArrayList<String>(Arrays.asList(ids.split(",")));
		Encontro encontro = encontroRepositorio.find(session.getIdEncontro());
		List<PapelNaEquipe> papelnaEquipe = encontro.getPapeisNaEquipe();
		for (PapelNaEquipe papelNaEquipe : papelnaEquipe) {
			papelNaEquipe.setOrdemImpressao(idsArray.indexOf(papelNaEquipe.getId().toString()));
		}
		result.use(Results.json()).from(new Mensagem("Ordenação Salva com Sucesso")).serialize();
	}
}