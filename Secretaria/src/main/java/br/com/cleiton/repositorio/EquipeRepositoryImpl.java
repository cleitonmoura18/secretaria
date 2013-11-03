package br.com.cleiton.repositorio;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.cleiton.modelo.Equipe;

@Component
public class EquipeRepositoryImpl
    extends Repository<Equipe, Long>
    implements EquipeRepository {

	EquipeRepositoryImpl(Session session) {
		super(session);
	}
}
