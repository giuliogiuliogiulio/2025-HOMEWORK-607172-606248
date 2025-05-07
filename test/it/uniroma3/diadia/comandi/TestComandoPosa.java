package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.*;
import static org.junit.jupiter.api.Assertions.*;
import it.uniroma3.diadia.giocatore.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import it.uniroma3.diadia.ambienti.*;
class TestComandoPosa {
	
	Partita partita;
	Attrezzo attrezzo;
	Stanza stanzaVuota;
	Comando comandoPosa;
	
	@BeforeEach
	public void setUp() {
		partita = new Partita();
		attrezzo = new Attrezzo("lanterna", 0);
		stanzaVuota = new Stanza("vuota");
		comandoPosa = new ComandoPosa();
		partita.getLabirinto().setStanzaCorrente(stanzaVuota);
	}
	

	@Test
	void testNessunAttrezzo() {
		comandoPosa.setParametro("lanterna");
		comandoPosa.esegui(partita);
		assertFalse(partita.getLabirinto().getStanzaCorrente().hasAttrezzo("lanterna"));
	}
	
	@Test
	void testAttrezzoPosato() {
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comandoPosa.setParametro("lanterna");
		comandoPosa.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("lanterna"));
		assertTrue(partita.getLabirinto().getStanzaCorrente().hasAttrezzo("lanterna"));
	}
	
	@Test
	void testParametroSbagliato() {
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comandoPosa.setParametro("osso");
		comandoPosa.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("lanterna"));
		assertFalse(partita.getLabirinto().getStanzaCorrente().hasAttrezzo("lanterna"));
	}

}
