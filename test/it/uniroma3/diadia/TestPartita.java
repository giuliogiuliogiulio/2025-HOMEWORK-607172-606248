package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPartita {
	
	Partita testPartita;

	@BeforeEach
	void setUp() throws Exception {
		this.testPartita = new Partita();
	}
	
	@Test
	void playerExists() {
		assertTrue(this.testPartita.getGiocatore() != null);
	}
	
	@Test
	void testIsFinitaDefault() {
		assertFalse(this.testPartita.isFinita());
	}
	
	@Test
	void testIsFinita() {
		this.testPartita.setFinita();
		assertTrue(this.testPartita.isFinita());
	}
	
	@Test
	void testVinta() {
		Labirinto lab = this.testPartita.getLabirinto();
		lab.setStanzaCorrente(lab.getStanzaVincente());
		assertTrue(this.testPartita.vinta());
	}

}
