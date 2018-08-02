package br.com.cleiton.controlador;

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
import br.com.cleiton.components.EnumMensagem;
import br.com.cleiton.components.Mensagem;
import br.com.cleiton.components.UsuarioSession;
import br.com.cleiton.modelo.Encontro;
import br.com.cleiton.modelo.Equipe;
import br.com.cleiton.modelo.ListaParticipacao;
import br.com.cleiton.modelo.Paroquia;
import br.com.cleiton.modelo.Participacao;
import br.com.cleiton.modelo.Pessoa;
import br.com.cleiton.repositorio.EncontroRepository;
import br.com.cleiton.repositorio.EquipeRepository;
import br.com.cleiton.repositorio.PartipacaoRepository;
import br.com.cleiton.repositorio.PessoaRepository;

@Resource
public class ParticipacaoController {

	private final Result result;
	private final PartipacaoRepository repository;
	private final EncontroRepository encontroRepository;
	private final EquipeRepository equipeRepository;
	private final UsuarioSession session;
	private final Validator validator;
	private final PessoaRepository pessoaRepository;
	

	public ParticipacaoController(Result result,
			PartipacaoRepository repository,
			EncontroRepository encontroRepository, UsuarioSession session,
			Validator validator, PessoaRepository pessoaRepository,EquipeRepository equipeRepository ) {
		super();
		this.result = result;
		this.repository = repository;
		this.encontroRepository = encontroRepository;
		this.session = session;
		this.validator = validator;
		this.pessoaRepository = pessoaRepository;
		this.equipeRepository = equipeRepository;
	}

	@Get("/participacaos")
	public List<Participacao> index() {
		return repository.findAll();
	}
	
	@Post("/participacaos")
	public void create(Participacao participacao) {
		Equipe equipe = equipeRepository.find(participacao.getEquipe().getId());
		validator.validate(participacao);
		Equipe circulo = participacao.getPessoa().getCirculo();
		if(equipe.getEncontro().isImprimirCirculo() && circulo.getId() == null){
			participacao.getPessoa().setCirculo(null);
		}
		if(equipe.isPrecisaPapelNaEquipe()){
			if(participacao.getPapelNaEquipe().getId() == null){
		 	result.use(Results.json()).from(new Mensagem(EnumMensagem.ERRO,"Faltando Informar Papel na Equipe")).serialize();
		 	return ;
			}
		}else{
			participacao.setPapelNaEquipe(null);
		}
		validator.onErrorUsePageOf(this).newPartipacao(participacao.getEquipe().getId());
		participacao.getPessoa().setParoquia(new Paroquia(session.getIdParoquia()));
		
		pessoaRepository.create(participacao.getPessoa());
		
		repository.create(participacao);
		result.use(Results.json()).from(new Mensagem("Salvo com Sucesso")).serialize();
	}
	
	@Get("equipe/{equipeId}/participacaos/new")
	public Participacao newPartipacao(Long equipeId) {
		Participacao participacao = new Participacao();
		Encontro encontro = encontroRepository.find(session.getIdEncontro());
		result.include("papeis",encontro.getPapeisNaEquipe());
		result.include("circulos",encontro.getCirculos());
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
		Equipe equipe = equipeRepository.find(participacao.getEquipe().getId());
		validator.validate(participacao);
		Equipe circulo = participacao.getPessoa().getCirculo();
		if(equipe.getEncontro().isImprimirCirculo() && circulo.getId() == null){
			participacao.getPessoa().setCirculo(null);
		}
		if(equipe.isPrecisaPapelNaEquipe()){
			if(equipe.getId() == null){
		 	result.use(Results.json()).from(new Mensagem(EnumMensagem.ERRO,"Salvo com Sucesso")).serialize();
		 	return ;
			}
		}else{
			participacao.setPapelNaEquipe(null);
		}
		validator.onErrorUsePageOf(this).edit(participacao);
		
		participacao.getPessoa().setParoquia(new Paroquia(session.getIdParoquia()));
		pessoaRepository.update(participacao.getPessoa());
		repository.update(participacao);
		result.include("mensagem","Salvo com Sucesso");
		result.use(Results.json()).from(new Mensagem("Salvo com Sucesso")).serialize();
		
	}
	
	@Get("/participacaos/{participacao.id}/edit")
	public Participacao edit(Participacao participacao) {
		Encontro encontro = encontroRepository.find(session.getIdEncontro());
		result.include("papeis",encontro.getPapeisNaEquipe());
		result.include("circulos",encontro.getCirculos());
		return repository.find(participacao.getId());
	}

	@Get("/participacaos/{participacao.id}")
	public Participacao show(Participacao participacao) {
		return repository.find(participacao.getId());
	}

	@Delete("/participacaos/{participacao.id}")
	public void destroy(Participacao participacao) {
		Participacao find = repository.find(participacao.getId());
		repository.destroy(find);
		result.redirectTo(EquipeController.class).show(find.getEquipe());  
	}
	
	@Post("/equipes/participacao/novas")
	public void salvarParticipacao(ListaParticipacao listaParticipacao) {
		
		
		boolean semPapelInformado = listaParticipacao.getPapelNaEquipe().getId() == null;
		boolean precisaPapelNaEquipe = listaParticipacao.getEquipe().isPrecisaPapelNaEquipe();
		if (precisaPapelNaEquipe && semPapelInformado) {
			validator.add(new ValidationMessage("Selecione o papel na equipe", ""));
		}
	
	    validator.onErrorUsePageOf(EquipeController.class).veteranos(listaParticipacao.getEquipe());

		if (!precisaPapelNaEquipe && semPapelInformado) {
			listaParticipacao.setPapelNaEquipe(null);
		}
		if(listaParticipacao.getPessoas()!=null || !listaParticipacao.getPessoas().isEmpty()){
			
			for (Pessoa pessoa : listaParticipacao.getPessoas()) {
				
				Participacao participacao = new Participacao();
				participacao.setEquipe(listaParticipacao.getEquipe());
				participacao.setPapelNaEquipe(listaParticipacao.getPapelNaEquipe());
				participacao.setPessoa(pessoa);
				repository.create(participacao);
			}
		}
		result.redirectTo(EquipeController.class).veteranos(listaParticipacao.getEquipe());
	}
}