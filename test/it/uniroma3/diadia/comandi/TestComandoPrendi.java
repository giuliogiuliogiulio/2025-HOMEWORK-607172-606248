package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestComandoPrendi {

	Comando prendi;

	Partita p;
	Stanza stanzaVuota, stanzaSingolo, stanzaDue;

	String attr, attr2;

	@BeforeEach
	void setUp() throws Exception {
		attr = "ogg";
		attr2 = "ogg2";

		stanzaVuota = new Stanza("vuota");

		stanzaSingolo = new Stanza("singolo");
		stanzaSingolo.addAttrezzo(new Attrezzo(attr, 0));

		stanzaDue = new Stanza("due");
		stanzaDue.addAttrezzo(new Attrezzo(attr, 0));
		stanzaDue.addAttrezzo(new Attrezzo(attr2, 0));

		prendi = new ComandoPrendi();
		prendi.setIO(new IOConsole());
		
		LabirintoBuilder b = Labirinto.newBuilder();
		b.addStanza("stanza");
		p = new Partita(b.getLabirinto());
	}

	@Test
	void testPrendiOgg() {
		p.getLabirinto().setStanzaCorrente(stanzaSingolo);
		prendi.setParametro(attr);
		prendi.esegui(p);
		assertFalse(p.getLabirinto().getStanzaCorrente().hasAttrezzo(attr));
		assertTrue(p.getGiocatore().getBorsa().hasAttrezzo(attr));
	}

	@Test
	void testPrendiOggVuoto() {
		p.getLabirinto().setStanzaCorrente(stanzaVuota);
		prendi.setParametro(attr);
		prendi.esegui(p);
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo(attr));
	}

	@Test
	void testPrendiOggDue() {
		p.getLabirinto().setStanzaCorrente(stanzaDue);
		prendi.setParametro(attr);
		prendi.esegui(p);
		assertTrue(p.getLabirinto().getStanzaCorrente().hasAttrezzo(attr2));
		assertFalse(p.getLabirinto().getStanzaCorrente().hasAttrezzo(attr));
	}

	@Test
	void TestPrendiOggBorsaPiena() {
		
		Stanza stanzaOggPesanti = new Stanza("stanza");
		p.getLabirinto().setStanzaCorrente(stanzaOggPesanti);

		int pMax = p.getGiocatore().getBorsa().getPesoMax();
		Attrezzo pesante = new Attrezzo("pesante", pMax);
		p.getLabirinto().getStanzaCorrente().addAttrezzo(pesante);
		
		prendi.setParametro(pesante.getNome());
		prendi.esegui(p);
		
		Attrezzo attrezzo = new Attrezzo("attrezzo",1);
		p.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzo);
		
		prendi.setParametro(attrezzo.getNome());
		prendi.esegui(p);
		
		assertTrue(p.getLabirinto().getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome()));
	}

}
