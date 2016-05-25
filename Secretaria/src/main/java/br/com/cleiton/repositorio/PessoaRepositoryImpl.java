package br.com.cleiton.repositorio;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.cleiton.modelo.Encontro;
import br.com.cleiton.modelo.Pessoa;

@Component
public class PessoaRepositoryImpl
    extends Repository<Pessoa, Long>
    implements PessoaRepository {

	PessoaRepositoryImpl(Session session) {
		super(session);
	}

	public List<Pessoa> getPessoasQueNaoParticipaDoEncontro(Encontro encontro) {
		StringBuffer varname1 = new StringBuffer();
		varname1.append("select par.pessoa_id from participacao par inner join ");
		varname1.append("equipe eq on eq.id=par.equipe_id ");
		varname1.append("inner join encontro en on en.id=eq.encontro_id ");
		varname1.append("where en.id= :idEncontro");
		List<BigInteger> ids = session.createSQLQuery(varname1.toString()).
				setParameter("idEncontro", encontro.getId()).list();
		List<Long> idsConvetidos = new ArrayList<Long>();
		for (BigInteger bigInteger : ids) {
			idsConvetidos.add(bigInteger.longValue());
		}
		Criteria createCriteria = session.createCriteria(Pessoa.class);
		if(!ids.isEmpty()){
			createCriteria
			.add(Restrictions.not(Restrictions.in("id", idsConvetidos)));
		}
		return createCriteria.list();

	}
}
