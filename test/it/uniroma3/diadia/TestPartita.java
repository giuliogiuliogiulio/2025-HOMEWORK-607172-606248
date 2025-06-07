package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

class TestPartita {

	Partita testPartita;

	Labirinto lab;

	Stanza iniziale, vittoria;

	@BeforeEach
	void setUp() throws Exception {
		iniziale = new Stanza("iniziale");
		vittoria = new Stanza("vittoria");
		LabirintoBuilder b = Labirinto.newBuilder();
		lab = b.addStanza(iniziale).addStanza(vittoria).addStanzaIniziale(iniziale.getNome())
				.addStanzaVincente(vittoria.getNome()).getLabirinto();
		this.testPartita = new Partita(lab);
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
		lab.setStanzaCorrente(lab.getStanzaVincente());
		assertTrue(this.testPartita.vinta());
	}

	@Test
	void testLabirinto() {
		assertEquals(this.testPartita.getLabirinto(), this.lab);
	}

	@Test
	void testStanzaCorrente() {
		Stanza s = new Stanza("s");
		lab.setStanzaCorrente(s);
		assertEquals(lab.getStanzaCorrente(), s);
	}

	@Test
	void setLabirinto() {
		assertNotNull(this.testPartita.getLabirinto());
		Labirinto l = null;
		testPartita.setLabirinto(l);
		assertNull(this.testPartita.getLabirinto());
	}

}
