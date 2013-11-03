package br.com.cleiton.repositorio;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.cleiton.modelo.Pessoa;

@Component
public class PessoaRepositoryImpl
    extends Repository<Pessoa, Long>
    implements PessoaRepository {

	PessoaRepositoryImpl(Session session) {
		super(session);
	}
}
