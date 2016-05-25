package br.com.cleiton.controlador;

import java.io.File;
import java.io.IOException;
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
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;
import br.com.cleiton.components.Mensagem;
import br.com.cleiton.components.UsuarioSession;
import br.com.cleiton.components.Utils;
import br.com.cleiton.modelo.Encontro;
import br.com.cleiton.modelo.Equipe;
import br.com.cleiton.modelo.ListaParticipacao;
import br.com.cleiton.modelo.Participacao;
import br.com.cleiton.modelo.Pessoa;
import br.com.cleiton.modelo.TipoPessoa;
import br.com.cleiton.repositorio.EncontroRepository;
import br.com.cleiton.repositorio.EquipeRepository;
import br.com.cleiton.repositorio.PessoaRepository;
import br.com.cleiton.word.MontarQuadrante;

@Resource
public class EquipeController {

	private final Result result;
	private final EquipeRepository repository;
	private final EncontroRepository encontroRepositorio;
	private final PessoaRepository pessoaRepository;
	private final UsuarioSession session;

	private final Validator validator;

	

	public EquipeController(Result result, EquipeRepository repository,
			EncontroRepository encontroRepositorio,
			PessoaRepository pessoaRepository, UsuarioSession session,
			Validator validator) {
		super();
		this.result = result;
		this.repository = repository;
		this.encontroRepositorio = encontroRepositorio;
		this.pessoaRepository = pessoaRepository;
		this.session = session;
		this.validator = validator;
	}
	@Get("migrarQuadrante")
	public Download criarQuadrantePorXLSX() throws Exception{
		File file = new File("C:\\Users\\clebert\\Desktop\\EJC\\Participantes EJC.xlsx");
		MontarQuadrante montarQuadrante = new MontarQuadrante(pessoaRepository);
		Encontro encontro = encontroRepositorio.find(session.getIdEncontro());
		return montarQuadrante.criarEncontro(file, encontro);
	}
	@Get("/equipes")
	public Encontro index() {

		Encontro encontro = encontroRepositorio.find(session.getIdEncontro());
		Collections.sort(encontro.getEquipes());
		return encontro;
	}

	@Post("/equipes")
	public void create(Equipe equipe) {
		validator.validate(equipe);
		validator.onErrorUsePageOf(this).newEquipe();
		Encontro encontro = encontroRepositorio.find(session.getIdEncontro());
		equipe.setEncontro(encontro);
		equipe.setOrdemImpressao(encontro.getEquipes().size());
		repository.create(equipe);
		result.redirectTo(EncontroController.class).show(encontro);
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
		List<String> idsArray = new ArrayList<String>(Arrays.asList(ids
				.split(",")));
		Encontro encontro = encontroRepositorio.find(session.getIdEncontro());
		List<Equipe> equipes = encontro.getEquipes();

		for (Equipe equipe : equipes) {
			equipe.setOrdemImpressao(idsArray
					.indexOf(equipe.getId().toString()));
		}
		result.use(Results.json())
				.from(new Mensagem("Ordenação Salva com Sucesso")).serialize();
	}

	@Get("/equipes/new")
	public Equipe newEquipe() {
		return new Equipe();
	}

	@Put("/equipes")
	public void update(Equipe equipe) {
		validator.validate(equipe);
		validator.onErrorUsePageOf(this).edit(equipe);
		Encontro encontro = encontroRepositorio.find(session.getIdEncontro());
		equipe.setEncontro(encontro);
		repository.update(equipe);
		result.redirectTo(EncontroController.class).show(encontro);
	}

	@Get("/equipes/{equipe.id}/edit")
	public Equipe edit(Equipe equipe) {

		return repository.find(equipe.getId());
	}

	@Get("/equipes/{equipe.id}")
	public Equipe show(Equipe equipe) {

		Equipe equipeView = repository.find(equipe.getId());
		equipeView.getPartipacao();
		result.include("participacaoList", equipeView.getPartipacao());
		return equipeView;
	}
	
	@Get("/equipes/copia/{equipe.id}")
	public ListaParticipacao veteranos(Equipe equipe) {
		Equipe equipeView = repository.find(equipe.getId());
		ListaParticipacao listaParticipacao = new ListaParticipacao();
		listaParticipacao.setEquipe(equipeView);
		List<Pessoa> pessoas = pessoaRepository.getPessoasQueNaoParticipaDoEncontro(equipeView.getEncontro());
		Collections.sort(pessoas);
		listaParticipacao.setPessoas(pessoas);
		return listaParticipacao;
	}

	
	@Delete("/equipes/{equipe.id}")
	public void destroy(Equipe equipe) {
		repository.destroy(repository.find(equipe.getId()));
		Encontro encontro = encontroRepositorio.find(session.getIdEncontro());
		result.redirectTo(EncontroController.class).show(encontro);
	}

	@Get({"/cracha/{idEquipe}","/crachas"})
	public Download nomeCracha(Long idEquipe) throws IOException {
		List<String> nomeCracahas = new ArrayList<String>();
		nomeCracahas.add("Nome;Equipe");
		String nomearquivo= "Crachás ";
		if (idEquipe != null) {
			Equipe equipe = repository.find(idEquipe);
			inserirEquipe(nomeCracahas, equipe);
			nomearquivo+=equipe.getName();
		}else{
			Encontro encontro = encontroRepositorio.find(session.getIdEncontro());
			for (Equipe equipe : encontro.getEquipes()) {
				inserirEquipe(nomeCracahas, equipe);
			}
			nomearquivo+= "Todos participantes";
		}
		return Utils.createXLSX(nomeCracahas, nomearquivo);
	}
	@Get({"/cracha/servos"})
	public Download nomeCracahServos() throws IOException {
		return crachasServosEEncontristas(true);
	}
	
	@Get({"/cracha/encontristas"})
	public Download nomeCrachaEncontristas() throws IOException {
		return crachasServosEEncontristas(false);
	}

	private Download crachasServosEEncontristas(Boolean servos)
			throws IOException {
		List<String> nomeCracahas = new ArrayList<String>();
		nomeCracahas.add("Nome;Equipe");
		String nomearquivo = "Crachás ";

		Encontro encontro = encontroRepositorio.find(session.getIdEncontro());
		for (Equipe equipe : encontro.getEquipes()) {
			if (servos) {
				if (!equipe.isCirculo()) {
					
					inserirEquipe(nomeCracahas, equipe);
				}
			} else {
				if (equipe.isCirculo()) {
					inserirEquipe(nomeCracahas, equipe);
				}
			}
		}
		if(servos)
			nomearquivo += "Crachas Servos";
		else
			nomearquivo += "Crachas Encontristas";
		return Utils.createXLSX(nomeCracahas, nomearquivo);
	}


	private void inserirEquipe(List<String> nomeCracahas, Equipe equipe) {
		for (Participacao participacao : equipe.getPartipacao()) {
			if(participacao.getPessoa().getTipoPessoa().equals(TipoPessoa.CASAL)){
				nomeCracahas.add("Tio " +participacao.getPessoa().getNomeCracha()+" e Tia "+participacao.getPessoa().getNomeCrachaConjugue()+" "+Utils.DELIMITADOR+" "+equipe.getName());
				nomeCracahas.add("Tia "+ participacao.getPessoa().getNomeCrachaConjugue()+" e Tio "+participacao.getPessoa().getNomeCracha()+" "+Utils.DELIMITADOR+" "+equipe.getName());
			}else{
				nomeCracahas.add(participacao.getPessoa().getNomeCracha()+" "+Utils.DELIMITADOR+" "+equipe.getName());
			}
		}
	}

}