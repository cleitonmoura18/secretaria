package br.com.cleiton.repositorio;

import java.util.List;

import br.com.cleiton.modelo.Pessoa;

public interface PessoaRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Pessoa entity);
	
	Pessoa update(Pessoa entity);
	
	void destroy(Pessoa entity);
	
	Pessoa find(Long id);
	
	List<Pessoa> findAll();

}
