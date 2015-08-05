package br.com.cleiton.repositorio;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.cleiton.modelo.sistema.Usuario;

@Component
public class UsuarioRepositoryImpl extends Repository<Usuario, Long>implements UsuarioRepository {

	UsuarioRepositoryImpl(Session session) {
		super(session);
	}

	@Override
	public Usuario findByLogin(String login) {
		return (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("login", login)).uniqueResult();
	}

	@Override
	public Usuario findByEmail(String email) {
		return (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("email", email)).uniqueResult();
	}
}
