package br.com.cleiton.repositorio;

import java.util.List;

import br.com.cleiton.modelo.Equipe;

public interface EquipeRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Equipe entity);
	
	Equipe update(Equipe entity);
	
	void destroy(Equipe entity);
	
	Equipe find(Long id);
	
	List<Equipe> findAll();

}
