package br.com.cleiton.repositorio;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.cleiton.modelo.Encontro;

@Component
public class EncontroRepositoryImpl
    extends Repository<Encontro, Long>
    implements EncontroRepository {

	EncontroRepositoryImpl(Session session) {
		super(session);
	}
}
