package br.com.cleiton.repositorio;

import java.util.List;

import br.com.cleiton.modelo.Participacao;

public interface PartipacaoRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Participacao entity);
	
	Participacao update(Participacao entity);
	
	void destroy(Participacao entity);
	
	Participacao find(Long id);
	
	List<Participacao> findAll();

}
