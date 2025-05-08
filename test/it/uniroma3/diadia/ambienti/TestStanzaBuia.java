package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestStanzaBuia {

	StanzaBuia buia;

	Attrezzo luce;

	@BeforeEach
	void setUp() throws Exception {
		luce = new Attrezzo("luce", 0);
		buia = new StanzaBuia("buia", luce.getNome());
	}

	@Test
	void testBuio() {
		assertEquals(buia.getDescrizione(), "qui c'è buio pesto");
	}

	@Test
	void testLuce() {
		buia.addAttrezzo(luce);
		assertNotEquals(buia.getDescrizione(), "qui c'è buio pesto");
	}

	@Test
	void testLuceRimuovi() {
		buia.addAttrezzo(luce);
		buia.removeAttrezzo(luce);
		assertEquals(buia.getDescrizione(), "qui c'è buio pesto");
	}

	@Test
	void testAttrezzoSbagliato() {
		Attrezzo sbagliato = new Attrezzo("sbagliato", 0);
		buia.addAttrezzo(sbagliato);
		assertEquals(buia.getDescrizione(), "qui c'è buio pesto");
	}

}
