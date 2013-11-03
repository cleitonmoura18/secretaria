package br.com.cleiton.controlador;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.cleiton.components.UsuarioSession;
import br.com.cleiton.modelo.Encontro;
import br.com.cleiton.modelo.Equipe;
import br.com.cleiton.modelo.Paroquia;
import br.com.cleiton.modelo.Participacao;
import br.com.cleiton.repositorio.EncontroRepository;
import br.com.cleiton.repositorio.PartipacaoRepository;
import br.com.cleiton.repositorio.PessoaRepository;

@Resource
public class ParticipacaoController {

	private final Result result;
	private final PartipacaoRepository repository;
	private final EncontroRepository encontroRepository;
	private final UsuarioSession session;
	private final Validator validator;
	private final PessoaRepository pessoaRepository;
	

	public ParticipacaoController(Result result,
			PartipacaoRepository repository,
			EncontroRepository encontroRepository, UsuarioSession session,
			Validator validator, PessoaRepository pessoaRepository) {
		super();
		this.result = result;
		this.repository = repository;
		this.encontroRepository = encontroRepository;
		this.session = session;
		this.validator = validator;
		this.pessoaRepository = pessoaRepository;
	}

	@Get("/participacaos")
	public List<Participacao> index() {
		return repository.findAll();
	}
	
	@Post("/participacaos")
	public void create(Participacao participacao) {
		validator.validate(participacao);
		validator.onErrorUsePageOf(this).newPartipacao(participacao.getEquipe().getId());
		participacao.getPessoa().setParoquia(new Paroquia(session.getIdParoquia()));
		pessoaRepository.create(participacao.getPessoa());
		
		repository.create(participacao);
		result.redirectTo(this).index();
	}
	
	@Get("equipe/{equipeId}/participacaos/new")
	public Participacao newPartipacao(Long equipeId) {
		Participacao participacao = new Participacao();
		Encontro encontro = encontroRepository.find(session.getIdEncontro());
		result.include("papeis",encontro.getPapeisNaEquipe());
		sair:
		for (Equipe equipe : encontro.getEquipes()) {
			if(equipe.getId().equals(equipeId)){
				participacao.setEquipe(equipe);
				break sair;}
						
		}
		return participacao;
	}
	
	@Put("/participacaos")
	public void update(Participacao participacao) {
		validator.validate(participacao);
		validator.onErrorUsePageOf(this).edit(participacao);
		participacao.getPessoa().setParoquia(new Paroquia(session.getIdParoquia()));
		pessoaRepository.create(participacao.getPessoa());
		repository.update(participacao);
		result.redirectTo(this).index();
	}
	
	@Get("/participacaos/{participacao.id}/edit")
	public Participacao edit(Participacao participacao) {
		
		return repository.find(participacao.getId());
	}

	@Get("/participacaos/{participacao.id}")
	public Participacao show(Participacao participacao) {
		return repository.find(participacao.getId());
	}

	@Delete("/participacaos/{participacao.id}")
	public void destroy(Participacao participacao) {
		repository.destroy(repository.find(participacao.getId()));
		result.redirectTo(this).index();  
	}
}