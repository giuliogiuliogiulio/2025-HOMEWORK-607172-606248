package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

class TestComandoVai {

	Comando vai;

	Partita p;

	@BeforeEach
	void setUp() throws Exception {
		vai = new ComandoVai(new IOConsole());
		Stanza centro = new Stanza("semplice");
		centro.impostaStanzaAdiacente("nord", new Stanza("nord"));
		centro.impostaStanzaAdiacente("loop", centro);

		p = new Partita();
		p.getLabirinto().setStanzaCorrente(centro);
	}

	@Test
	void testDirezioneLecita() {
		Stanza curr = p.getLabirinto().getStanzaCorrente();
		vai.setParametro("nord");
		vai.esegui(p);
		assertEquals(p.getLabirinto().getStanzaCorrente().getNome(), "nord");
		assertNotEquals(p.getLabirinto().getStanzaCorrente(), curr);
	}

	@Test
	void testDirezioneNonLecita() {
		Stanza curr = p.getLabirinto().getStanzaCorrente();
		vai.setParametro("direzione non lecita");
		vai.esegui(p);
		assertEquals(curr, p.getLabirinto().getStanzaCorrente());
	}

	@Test
	void testLoop() {
		Stanza curr = p.getLabirinto().getStanzaCorrente();
		vai.setParametro("loop");
		vai.esegui(p);
		assertEquals(curr, p.getLabirinto().getStanzaCorrente());
	}

}
