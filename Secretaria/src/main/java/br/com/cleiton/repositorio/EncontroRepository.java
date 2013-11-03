package br.com.cleiton.repositorio;

import java.util.List;

import br.com.cleiton.modelo.Encontro;

public interface EncontroRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Encontro entity);
	
	Encontro update(Encontro entity);
	
	void destroy(Encontro entity);
	
	Encontro find(Long id);
	
	List<Encontro> findAll();

}
