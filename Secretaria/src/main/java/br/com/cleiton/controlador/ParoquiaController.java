package br.com.cleiton.controlador;

import java.util.List;

import br.com.cleiton.components.UsuarioSession;
import br.com.cleiton.modelo.Paroquia;
import br.com.cleiton.repositorio.ParoquiaRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class ParoquiaController {

	private final Result result;
	private final ParoquiaRepository repository;
	private final UsuarioSession usuarioSession;

	private final Validator validator;
	
	
	public ParoquiaController(Result result, ParoquiaRepository repository,
			Validator validator, UsuarioSession usuarioSession) {
		this.result = result;
		this.repository = repository;
		this.usuarioSession = usuarioSession;
		this.validator = validator;
	}

	@Get({"/paroquias",""})
	public List<Paroquia> index() {
		return repository.findAll();
	}

	@Post("/paroquias")
	public void create(Paroquia paroquia) {
		validator.validate(paroquia);
		validator.onErrorUsePageOf(this).newParoquia();
		repository.create(paroquia);
		result.redirectTo(this).index();
	}

	@Get("/paroquias/new")
	public Paroquia newParoquia() {
		return new Paroquia();
	}

	@Put("/paroquias")
	public void update(Paroquia paroquia) {
		validator.validate(paroquia);
		validator.onErrorUsePageOf(this).edit(paroquia);
		repository.update(paroquia);
		result.redirectTo(this).index();
	}

	@Get("/paroquias/{paroquia.id}/edit")
	public Paroquia edit(Paroquia paroquia) {

		return repository.find(paroquia.getId());
	}

	@Get("/paroquias/{paroquia.id}")
	public Paroquia show(Paroquia paroquia) {
		return repository.find(paroquia.getId());
	}

	@Delete("/paroquias/{paroquia.id}")
	public void destroy(Paroquia paroquia) {
		repository.destroy(repository.find(paroquia.getId()));
		result.redirectTo(this).index();
	}

	@Get("paroquias/{paroquiaId}/listaEncontros")
	public Paroquia listEncontros(Long paroquiaId) {
		usuarioSession.setIdParoquia(paroquiaId);
		Paroquia paroquia = repository.find(paroquiaId);
		result.include("encontroList", paroquia.getEncontros());
		return paroquia;
	}
}