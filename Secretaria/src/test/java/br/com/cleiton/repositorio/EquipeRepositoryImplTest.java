package br.com.cleiton.repositorio;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class EquipeRepositoryImplTest {

    @Test public void fakeTest() {
  		assertNotNull("put something real.", new EquipeRepositoryImpl(null));
  	}
}

