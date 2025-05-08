package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

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

	@Test
	void testStanzaAdiacenteAtrioNord_isBiblioteca() {
		this.labirinto.creaStanze();
		Stanza corrente = this.labirinto.getStanzaCorrente();
		Stanza biblio = corrente.getStanzaAdiacente("nord");
		assertEquals("Biblioteca", biblio.getNome());
	}

	@Test
	void testAttrezzoInAtrio_isOsso() {
		this.labirinto.creaStanze();
		Stanza corrente = this.labirinto.getStanzaCorrente();
		Attrezzo osso = corrente.getAttrezzo("osso");
		assertEquals("osso", osso.getNome());
	}

	@Test
	void testAttrezzoInAulaN10_isLanterna() {
		this.labirinto.creaStanze();
		Stanza corrente = this.labirinto.getStanzaCorrente();
		Stanza n10 = corrente.getStanzaAdiacente("sud");
		Attrezzo lanterna = n10.getAttrezzo("lanterna");
		assertEquals("lanterna", lanterna.getNome());
	}

}
