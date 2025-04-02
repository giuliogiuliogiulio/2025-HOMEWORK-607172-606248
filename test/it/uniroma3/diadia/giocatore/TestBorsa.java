package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestBorsa {
	
	Borsa borsaTest;
	
	Attrezzo attrezzoTest1, attrezzoTest2;
	

	@BeforeEach
	void setUp() throws Exception {
		this.borsaTest = new Borsa();
		this.attrezzoTest1 = new Attrezzo("attrezzoTest1", 1);
		this.attrezzoTest2 = new Attrezzo("attrezzoTest2", 2);
	}
	
	@Test
	void testAggiuntaAttrezzo1() {
		this.borsaTest.addAttrezzo(attrezzoTest1);
		assertTrue(borsaTest.hasAttrezzo(attrezzoTest1.getNome()));
	}
	
	@Test
	void testAggiuntaAttrezzo2() {
		this.borsaTest.addAttrezzo(attrezzoTest1);
		this.borsaTest.addAttrezzo(attrezzoTest2);
		boolean result = this.borsaTest.hasAttrezzo(attrezzoTest1.getNome()) && this.borsaTest.hasAttrezzo(attrezzoTest2.getNome());
		assertTrue(result);
	}
	
	@Test
	void testNonAggiunta() {
		assertFalse(this.borsaTest.hasAttrezzo(attrezzoTest1.getNome()));
	}
	
	@Test
	void testGetAttrezzo() {
		this.borsaTest.addAttrezzo(attrezzoTest1);
		Attrezzo a = this.borsaTest.getAttrezzo(attrezzoTest1.getNome());
		// getAttrezzo non deve rimuovere l'attrezzo dalla borsa, ritorna un riferimento non una copia quindi controllo con (==)
		boolean result = a == attrezzoTest1 && this.borsaTest.hasAttrezzo(attrezzoTest1.getNome());
		assertTrue(result);
	}
	
	@Test
	void testGetAttrezzo2() {
		this.borsaTest.addAttrezzo(attrezzoTest1);
		this.borsaTest.addAttrezzo(attrezzoTest2);
		Attrezzo a = this.borsaTest.getAttrezzo(attrezzoTest2.getNome());
		boolean result = a == attrezzoTest2 && this.borsaTest.hasAttrezzo(attrezzoTest2.getNome());
		assertTrue(result);
	}
	
	@Test
	void testEmptyTrue() {
		assertTrue(this.borsaTest.isEmpty());
	}
	
	@Test
	void testEmptyFalse() {
		this.borsaTest.addAttrezzo(attrezzoTest1);
		assertFalse(this.borsaTest.isEmpty());
	}
	
	@Test
	void testRimuovi1() {
		this.borsaTest.addAttrezzo(attrezzoTest1);
		Attrezzo a = this.borsaTest.removeAttrezzo(attrezzoTest1.getNome());
		boolean presente = this.borsaTest.hasAttrezzo(attrezzoTest1.getNome());
		assertTrue(a == attrezzoTest1 && !presente);
	}
	
	@Test
	void testRimuovi2() {
		this.borsaTest.addAttrezzo(attrezzoTest1);
		this.borsaTest.addAttrezzo(attrezzoTest2);
		Attrezzo a = this.borsaTest.removeAttrezzo(attrezzoTest2.getNome());
		boolean presente_at1 = this.borsaTest.hasAttrezzo(attrezzoTest1.getNome());
		boolean presente_at2 = this.borsaTest.hasAttrezzo(attrezzoTest2.getNome());
		assertTrue(a == attrezzoTest2 && presente_at1 && !presente_at2);
	}
	
	@Test
	void testRimuoviFalse() {
		assertTrue(null == this.borsaTest.removeAttrezzo(attrezzoTest1.getNome()));
	}
	
	@Test
	void testPeso1() {
		// peso della borsa con un attrezzo deve essere uguale al peso dell'oggetto
		this.borsaTest.addAttrezzo(attrezzoTest1);
		assertEquals(borsaTest.getPeso(), attrezzoTest1.getPeso());
	}
	
	@Test
	void testPeso2() {
		this.borsaTest.addAttrezzo(attrezzoTest1);
		this.borsaTest.addAttrezzo(attrezzoTest2);
		int pesoAtteso = attrezzoTest1.getPeso() + attrezzoTest2.getPeso();
		assertEquals(borsaTest.getPeso(), pesoAtteso);
	}
	
	@Test
	void testPesoRimozione() {
		this.borsaTest.addAttrezzo(attrezzoTest1);
		this.borsaTest.removeAttrezzo(attrezzoTest1.getNome());
		assertEquals(borsaTest.getPeso(), 0);
	}

}
