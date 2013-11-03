package br.com.cleiton.controlador;

import java.io.IOException;
import java.util.List;

import br.com.cleiton.components.UsuarioSession;
import br.com.cleiton.modelo.Encontro;
import br.com.cleiton.modelo.Paroquia;
import br.com.cleiton.repositorio.EncontroRepository;
import br.com.cleiton.repositorio.ParoquiaRepository;
import br.com.cleiton.word.ArquivoWord;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class EncontroController {

	private final Result result;
	private final EncontroRepository repository;
	private final ParoquiaRepository paroquiaRepository;
	private final UsuarioSession session;
	
	private final Validator validator;
	
	public EncontroController(Result result, EncontroRepository repository, ParoquiaRepository paroquiaRepository,
	Validator validator,UsuarioSession session) {
		this.result = result;
		this.repository = repository;
		this.paroquiaRepository=paroquiaRepository;
		this.validator = validator;
		this.session=session;
	}
	@Get("criarQuadrante")
	public void criarQuadrante(){
		ArquivoWord arquivoWord= new ArquivoWord();
		try {
			arquivoWord.criarQuadrante(repository.find(4l));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		result.redirectTo(ParoquiaController.class).listEncontros(encontro.getParoquia().getId());
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
		result.redirectTo(this).index();
	}
	
	@Get("/encontros/{encontro.id}/edit")
	public Encontro edit(Encontro encontro) {
		return repository.find(encontro.getId());
	}

	@Get("/encontros/{encontro.id}")
	public Encontro show(Encontro encontro) {
		session.setIdEncontro(encontro.getId());
		return repository.find(encontro.getId());
	}

	@Delete("/encontros/{encontro.id}")
	public void destroy(Encontro encontro) {
		repository.destroy(repository.find(encontro.getId()));
		result.redirectTo(this).index();  
	}

}