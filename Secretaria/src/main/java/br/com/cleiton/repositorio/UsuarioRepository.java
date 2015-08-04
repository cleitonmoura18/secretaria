package br.com.cleiton.repositorio;

import java.util.List;

import br.com.cleiton.modelo.sistema.Usuario;


public interface UsuarioRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Usuario entity);
	
	Usuario update(Usuario entity);
	
	void destroy(Usuario entity);
	
	Usuario find(Long id);
	
	List<Usuario> findAll();

}
