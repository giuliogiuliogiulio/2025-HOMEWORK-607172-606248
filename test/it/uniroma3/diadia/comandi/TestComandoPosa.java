package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

class TestComandoPosa {

	Partita partita;
	Attrezzo attrezzo;
	Stanza stanzaVuota, stanzaPiena;
	Comando comandoPosa;

	@BeforeEach
	public void setUp() {
		LabirintoBuilder b = Labirinto.newBuilder();
		b.addStanza("stanza");
		partita = new Partita(b.getLabirinto());
		attrezzo = new Attrezzo("lanterna", 0);
		stanzaVuota = new Stanza("vuota");

		stanzaPiena = new Stanza("piena");
		Attrezzo attrStanzaPiena = new Attrezzo("pieno", 0);
		while (stanzaPiena.addAttrezzo(attrStanzaPiena)) // cambiato il significato di stanza piena, una stanza non può contenere più copie di uno stesso oggetto
			;

		comandoPosa = new ComandoPosa();
		comandoPosa.setIO(new IOConsole());
		partita.getLabirinto().setStanzaCorrente(stanzaVuota);
	}

	@Test
	void testNessunAttrezzo() {
		comandoPosa.setParametro("niente");
		comandoPosa.esegui(partita);
		assertFalse(partita.getLabirinto().getStanzaCorrente().hasAttrezzo("niente"));
	}

	@Test
	void testAttrezzoPosato() {
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comandoPosa.setParametro(attrezzo.getNome());
		comandoPosa.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome()));
		assertTrue(partita.getLabirinto().getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
	}

	@Test
	void testParametroSbagliato() {
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comandoPosa.setParametro("osso");
		comandoPosa.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome()));
		assertFalse(partita.getLabirinto().getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
	}

	@Test
	void testStanzaPiena() {
		partita.getLabirinto().setStanzaCorrente(stanzaPiena);
		partita.getGiocatore().getBorsa().addAttrezzo(partita.getLabirinto().getStanzaCorrente().getAttrezzo("pieno"));
		
		comandoPosa.setParametro(partita.getLabirinto().getStanzaCorrente().getAttrezzo("pieno").getNome());
		comandoPosa.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("pieno"));
		assertTrue(partita.getLabirinto().getStanzaCorrente().hasAttrezzo("pieno"));
		
		partita.getLabirinto().setStanzaCorrente(stanzaVuota);
		partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzo);
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);

		comandoPosa.setParametro(attrezzo.getNome());
		comandoPosa.esegui(partita);
		
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("lanterna"));
		assertTrue(partita.getLabirinto().getStanzaCorrente().hasAttrezzo("lanterna"));
	}

}
