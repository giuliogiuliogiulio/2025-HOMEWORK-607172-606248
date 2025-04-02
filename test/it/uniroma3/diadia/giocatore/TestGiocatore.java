package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestGiocatore {
	Giocatore giocatore;

	@BeforeEach
	void setUp() throws Exception {
		giocatore = new Giocatore();
	}

	@Test
	void testGetBorsa() {
		assertTrue(this.giocatore.getBorsa() != null);
	}
	
	@Test
	void testGetCfu() {
		assertTrue(this.giocatore.getCfu() == 20);
	}
	
	@Test
	void testSetCfu() {
		this.giocatore.setCfu(5);
		assertTrue(this.giocatore.getCfu() == 5);
	}

}
