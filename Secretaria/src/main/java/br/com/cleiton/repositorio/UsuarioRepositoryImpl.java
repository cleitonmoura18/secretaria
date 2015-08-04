package br.com.cleiton.repositorio;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.cleiton.modelo.sistema.Usuario;

@Component
public class UsuarioRepositoryImpl extends Repository<Usuario, Long>implements UsuarioRepository {

	UsuarioRepositoryImpl(Session session) {
		super(session);
	}
}
