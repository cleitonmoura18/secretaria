package br.com.cleiton.repositorio;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.cleiton.modelo.PapelNaEquipe;

@Component
public class PapelNaEquipeRepositoryImpl
    extends Repository<PapelNaEquipe, Long>
    implements PapelNaEquipeRepository {

	PapelNaEquipeRepositoryImpl(Session session) {
		super(session);
	}
}
