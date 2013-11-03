package br.com.cleiton.controlador;

import java.util.List;

import br.com.cleiton.modelo.Partipacao;
import br.com.cleiton.repositorio.PartipacaoRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class PartipacaoController {

	private final Result result;
	private final PartipacaoRepository repository;
	
	private final Validator validator;
	
	public PartipacaoController(Result result, PartipacaoRepository repository, 
	Validator validator) {
		this.result = result;
		this.repository = repository;
	
		this.validator = validator;
	}
	
	@Get("/partipacaos")
	public List<Partipacao> index() {
		return repository.findAll();
	}
	
	@Post("/partipacaos")
	public void create(Partipacao partipacao) {
		validator.validate(partipacao);
		validator.onErrorUsePageOf(this).newPartipacao();
		repository.create(partipacao);
		result.redirectTo(this).index();
	}
	
	@Get("/partipacaos/new")
	public Partipacao newPartipacao() {
		return new Partipacao();
	}
	
	@Put("/partipacaos")
	public void update(Partipacao partipacao) {
		validator.validate(partipacao);
		validator.onErrorUsePageOf(this).edit(partipacao);
		repository.update(partipacao);
		result.redirectTo(this).index();
	}
	
	@Get("/partipacaos/{partipacao.id}/edit")
	public Partipacao edit(Partipacao partipacao) {
		
		return repository.find(partipacao.getId());
	}

	@Get("/partipacaos/{partipacao.id}")
	public Partipacao show(Partipacao partipacao) {
		return repository.find(partipacao.getId());
	}

	@Delete("/partipacaos/{partipacao.id}")
	public void destroy(Partipacao partipacao) {
		repository.destroy(repository.find(partipacao.getId()));
		result.redirectTo(this).index();  
	}
}