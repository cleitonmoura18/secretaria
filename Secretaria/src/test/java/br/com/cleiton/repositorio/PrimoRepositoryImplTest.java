package br.com.cleiton.repositorio;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PrimoRepositoryImplTest {

    @Test public void fakeTest() {
  		assertNotNull("put something real.", new PessoaRepositoryImpl(null));
  	}
}

