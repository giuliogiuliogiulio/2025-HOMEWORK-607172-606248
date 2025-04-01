package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TestStanza {
	
	private Stanza stanza;

	@BeforeEach
	void setUp() throws Exception {
		this.stanza = new Stanza("stanzaTest");
	}

	@Test
	void testStanzaAdiacente() {
		Stanza stanza = new Stanza("stanzaAdiacente");
		this.stanza.impostaStanzaAdiacente("direzione", stanza);
		assertEquals(stanza, this.stanza.getStanzaAdiacente("direzione"));
	}
	
	@Test
	void testAddAttrezzo() {
		Stanza stanza = new Stanza("stanzaProva");
		stanza.getAttrezzi();
	}

}
