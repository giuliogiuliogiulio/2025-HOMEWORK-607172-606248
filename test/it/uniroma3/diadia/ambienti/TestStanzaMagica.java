package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestStanzaMagica {
	
	StanzaMagica magica;
	
	int attrezziPosati;
	
	int soglia;

	@BeforeEach
	public void setUp() throws Exception {
		magica = new StanzaMagica("magica", 0);
	}
	
	@Test
	void testIsMagica() {
		assertTrue(magica.isMagica());
	}
	
	@Test
	void testModificaAttrezzo() {
		StringBuilder invertito = new StringBuilder("attrezzo").reverse();
		Attrezzo attrezzoInvertito = new Attrezzo(invertito.toString(), 0);
		Attrezzo attrezzo = new Attrezzo("attrezzo",0);
		assertEquals(attrezzoInvertito, magica.modificaAttrezzo(attrezzo));
	}
	
	@Test
	void testAddAttrezzo() {
		Attrezzo attrezzo = new Attrezzo("attrezzo", 0);
		magica.addAttrezzo(attrezzo);
		StringBuilder invertito = new StringBuilder("attrezzo").reverse();
		Attrezzo attrezzoInvertito = new Attrezzo(invertito.toString(), 0);
		assertTrue(magica.getAttrezzi().contains(attrezzoInvertito));
	}

}
