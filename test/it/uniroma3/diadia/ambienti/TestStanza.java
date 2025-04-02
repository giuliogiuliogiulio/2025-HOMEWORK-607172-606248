package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TestStanza {
	
	private Stanza stanza;

	@BeforeEach
	void setUp() throws Exception {
		this.stanza = new Stanza("stanzaTest");
	}
	// Test stanzaAdiacente()
	@Test 
	void testStanzaAdiacenteNessunaStanza(){
		assertEquals(null, this.stanza.getStanzaAdiacente("direzione"));
	}
	
	@Test
	void testStanzaAdiacenteUnaNuovaStanza() {
		Stanza stanzaAdiacente = new Stanza("stanzaAdiacente");
		this.stanza.impostaStanzaAdiacente("direzione", stanzaAdiacente);
		assertEquals(stanzaAdiacente, this.stanza.getStanzaAdiacente("direzione"));
	}
	
	@Test
	void testStanzaAdiacenteDueNuoveStanze() {
		Stanza stanza1 = new Stanza("stanzaAdiacente1");
		Stanza stanza2 = new Stanza("stanzaAdiacente2");
		this.stanza.impostaStanzaAdiacente("nord", stanza1);
		this.stanza.impostaStanzaAdiacente("sud", stanza2);
		assertEquals(stanza1, this.stanza.getStanzaAdiacente("nord"));
		assertEquals(stanza2, this.stanza.getStanzaAdiacente("sud"));
	}
	
	// test AddAttrezzo()
	@Test
	void testAddAttrezzoNessunAttrezzo() {
		assertEquals(null, this.stanza.getAttrezzo("attrezzo"));
	}
	
	@Test
	void testAddAttrezzoSingoloAttrezzo() {
		Attrezzo attrezzo = new Attrezzo("attrezzo", 8);
		this.stanza.addAttrezzo(attrezzo); 
		assertEquals(attrezzo, this.stanza.getAttrezzo("attrezzo"));
	}
	
	@Test
	void testAddAttrezzoDueAttrezzi() {
		Attrezzo attrezzo1 = new Attrezzo("attrezzo1", 8);
		Attrezzo attrezzo2 = new Attrezzo("attrezzo2", 8);
		this.stanza.addAttrezzo(attrezzo1);
		this.stanza.addAttrezzo(attrezzo2);
		assertEquals(attrezzo2, this.stanza.getAttrezzo("attrezzo2"));
	}
	
	//test removeAttrezzo()
	@Test
	void testRemoveAttrezzoNessunAttrezzoRimosso() {
		Attrezzo attrezzo = new Attrezzo("attrezzo", 8);
		assertEquals(false, this.stanza.removeAttrezzo(attrezzo));
	}
	
	@Test
	void testRemoveAttrezzoRimossoSingoloAttrezzo() {
		Attrezzo attrezzo = new Attrezzo("attrezzo", 8);
		this.stanza.addAttrezzo(attrezzo);
		assertEquals(true, this.stanza.removeAttrezzo(attrezzo));
	}
	
	@Test
	void testRemoveAttrezzoGiàRimosso() {
		Attrezzo attrezzo = new Attrezzo("attrezzo", 8);
		this.stanza.removeAttrezzo(attrezzo);
		assertEquals(false, this.stanza.removeAttrezzo(attrezzo));
	}

}
