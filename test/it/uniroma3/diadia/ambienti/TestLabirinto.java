package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestLabirinto {
	Labirinto labirinto;
	Stanza stanzaCorrente;
	Stanza stanzaVincente;

	@BeforeEach
	void setUp() throws Exception {
		labirinto = new Labirinto();
	}

	@Test
	void testNuovoLabirinto() {
		assertEquals(null, this.labirinto.getStanzaVincente());
	}
	
	@Test
	void testGetStanzaVincente() {
		this.labirinto.creaStanze();
		Stanza vincente = this.labirinto.getStanzaVincente();
		assertEquals("Biblioteca", vincente.getNome());
	}
	
	@Test
	void testSetStanzaCorrente() {
		Stanza corrente = new Stanza("corrente");
		this.labirinto.setStanzaCorrente(corrente);
		assertEquals(corrente, this.labirinto.getStanzaCorrente());
	}

}
