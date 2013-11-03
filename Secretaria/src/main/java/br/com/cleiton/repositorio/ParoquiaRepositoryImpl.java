package br.com.cleiton.repositorio;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.cleiton.modelo.Paroquia;

@Component
public class ParoquiaRepositoryImpl
    extends Repository<Paroquia, Long>
    implements ParoquiaRepository {

	ParoquiaRepositoryImpl(Session session) {
		super(session);
	}
}
