package br.com.cleiton.repositorio;

import java.util.List;

import br.com.cleiton.modelo.Paroquia;

public interface ParoquiaRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Paroquia entity);
	
	Paroquia update(Paroquia entity);
	
	void destroy(Paroquia entity);
	
	Paroquia find(Long id);
	
	List<Paroquia> findAll();

}
