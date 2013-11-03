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
import br.com.cleiton.modelo.Paroquia;
import br.com.cleiton.modelo.Pessoa;
import br.com.cleiton.modelo.TipoPessoa;
import br.com.cleiton.repositorio.ParoquiaRepository;
import br.com.cleiton.repositorio.PessoaRepository;

@Resource
public class PessoaController {

	private final Result result;
	private final PessoaRepository repository;
	private final ParoquiaRepository paroquiaRepository;
	private final UsuarioSession session;

	private final Validator validator;

	public PessoaController(Result result, PessoaRepository repository,
			ParoquiaRepository paroquiaRepository, UsuarioSession session,
			Validator validator) {
		super();
		this.result = result;
		this.repository = repository;
		this.paroquiaRepository = paroquiaRepository;
		this.session = session;
		this.validator = validator;
	}

	@Get("/pessoas")
	public List<Pessoa> index() {
		return paroquiaRepository.find(session.getIdParoquia()).getPessoas();
	}

	@Post("/pessoas")
	public void create(Pessoa pessoa) {
		validator.validate(pessoa);
		validator.onErrorUsePageOf(this).newPessoa(null);
		pessoa.setParoquia(new Paroquia(session.getIdParoquia()));
		repository.create(pessoa);
		result.redirectTo(this).index();
	}

	@Get("/pessoas/new/{tipo}")
	public Pessoa newPessoa(String tipo) {
		TipoPessoa tipoPessoa = null;
		if (tipo == null) {
			tipoPessoa = TipoPessoa.JOVEM;
		} else {
			if (tipo.equals("p"))
				tipoPessoa = TipoPessoa.PADRE;
			else {
				if (tipo.equals("j")) {
					tipoPessoa = TipoPessoa.JOVEM;

				} else {
					tipoPessoa = TipoPessoa.CASAL;

				}
			}
		}
		Pessoa pessoa = new Pessoa();
		pessoa.setTipoPessoa(tipoPessoa);
		return pessoa;
	}

	@Put("/pessoas")
	public void update(Pessoa pessoa) {
		validator.validate(pessoa);
		validator.onErrorUsePageOf(this).edit(pessoa);
		pessoa.setParoquia(new Paroquia(session.getIdParoquia()));
		repository.update(pessoa);
		result.redirectTo(this).index();
	}

	@Get("/pessoas/{pessoa.id}/edit")
	public Pessoa edit(Pessoa pessoa) {

		return repository.find(pessoa.getId());
	}

	@Get("/pessoas/{pessoa.id}")
	public Pessoa show(Pessoa pessoa) {
		return repository.find(pessoa.getId());
	}

	@Delete("/pessoas/{pessoa.id}")
	public void destroy(Pessoa pessoa) {
		repository.destroy(repository.find(pessoa.getId()));

		result.redirectTo(this).index();
	}
}