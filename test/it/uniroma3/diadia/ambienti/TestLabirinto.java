package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestLabirinto {
	Labirinto labirinto;

	@BeforeEach
	void setUp() throws Exception {
		labirinto = new Labirinto(null, null);
	}

	@Test
	void testNuovoLabirinto() {
		assertEquals(null, this.labirinto.getStanzaVincente());
	}

	@Test
	void testGetStanzaVincente() {
		Stanza vincente = this.labirinto.getStanzaVincente();
		assertNull(vincente);
	}

	@Test
	void testSetStanzaCorrente() {
		Stanza corrente = new Stanza("corrente");
		this.labirinto.setStanzaCorrente(corrente);
		assertEquals(corrente, this.labirinto.getStanzaCorrente());
	}

}
