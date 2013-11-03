package br.com.cleiton.repositorio;

import java.util.List;

import br.com.cleiton.modelo.Partipacao;

public interface PartipacaoRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Partipacao entity);
	
	Partipacao update(Partipacao entity);
	
	void destroy(Partipacao entity);
	
	Partipacao find(Long id);
	
	List<Partipacao> findAll();

}
