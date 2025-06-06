package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirintoTest {

	private final String monolocale = 
			"Stanze: aula\n"+
			"Magiche: \n"+
			"Buie: \n"+
			"Bloccate: \n" +
			"Inizio: aula\n"+
			"Vincente: aula\n"+
			"Maghi: \n"+
			"Cani: cane wolf aula\n"+
			"Streghe: \n"+
			"Attrezzi: \n"+
			"Uscite: \n";

	private final String bilocale = 
			"Stanze: N12, N11\n"+
			"Magiche: \n"+
			"Buie: \n"+
			"Bloccate: \n"+
			"Inizio: N12\n"+
			"Vincente: N11\n"+
			"Maghi: \n"+
			"Cani: \n"+
			"Streghe: \n"+
			"Attrezzi: martello 3 N12\n"+
			"Uscite: \n";
	
	private CaricatoreLabirinto cl;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMonolocale() throws FormatoFileNonValidoException, FileNotFoundException {
		cl = new CaricatoreLabirinto(monolocale);
		cl.carica();
		assertEquals("aula", this.cl.getStanzaIniziale().getNome());
		assertEquals("aula", this.cl.getStanzaVincente().getNome());
		}
	
	@Test
	public void testBilocale() throws FormatoFileNonValidoException, FileNotFoundException {
		cl = new CaricatoreLabirinto(bilocale);
		cl.carica();
		assertEquals("N12", this.cl.getStanzaIniziale().getNome());
		assertEquals("N11", this.cl.getStanzaVincente().getNome());
		}
	
	
	@Test
	public void testBilocaleAttrezzo() throws FormatoFileNonValidoException, FileNotFoundException {
		cl = new CaricatoreLabirinto(bilocale);
		cl.carica();
		Attrezzo expected = new Attrezzo("martello", 3);
		assertEquals(expected, this.cl.getStanzaIniziale().getAttrezzo("martello"));
		}
}
