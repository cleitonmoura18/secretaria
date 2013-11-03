package br.com.cleiton.repositorio;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.cleiton.modelo.Participacao;

@Component
public class PartipacaoRepositoryImpl
    extends Repository<Participacao, Long>
    implements PartipacaoRepository {

	PartipacaoRepositoryImpl(Session session) {
		super(session);
	}
}
