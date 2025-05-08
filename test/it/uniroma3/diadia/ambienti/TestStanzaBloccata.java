package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestStanzaBloccata {

	StanzaBloccata bloccata;
	
	Attrezzo chiave;
	
	String direzioneBloccata;
	
	@BeforeEach
	void setUp() throws Exception {
		chiave = new Attrezzo("chiave", 0);
		direzioneBloccata = "nord";
		bloccata = new StanzaBloccata("bloccata", direzioneBloccata, chiave.getNome());
	}

	@Test
	void testBloccata() {
		assertEquals(bloccata, bloccata.getStanzaAdiacente(direzioneBloccata));
	}
	
	@Test
	void testSbloccata() {
		bloccata.addAttrezzo(chiave);
		assertNotEquals(bloccata, bloccata.getStanzaAdiacente(direzioneBloccata));
	}
	
	@Test
	void testSbloccataChiaveSbagliata() {
		Attrezzo sbagliato = new Attrezzo("sbagliato", 0);
		bloccata.addAttrezzo(sbagliato);
		assertEquals(bloccata, bloccata.getStanzaAdiacente(direzioneBloccata));
	}
	
	@Test
	void testDescrizioneContieneInfo() {
		String descrizione = bloccata.getDescrizione();
		assertTrue(descrizione.contains(direzioneBloccata));
		assertTrue(descrizione.contains(chiave.getNome()));
	}

}
