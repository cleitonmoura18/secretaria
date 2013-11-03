package br.com.cleiton.repositorio;

import java.util.List;

import br.com.cleiton.modelo.PapelNaEquipe;

public interface PapelNaEquipeRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(PapelNaEquipe entity);
	
	PapelNaEquipe update(PapelNaEquipe entity);
	
	void destroy(PapelNaEquipe entity);
	
	PapelNaEquipe find(Long id);
	
	List<PapelNaEquipe> findAll();

}
