package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAutomatico {

	IOSimulator simulatore;
	DiaDia gioco;

	@BeforeEach
	void setUp() throws Exception {
		simulatore = new IOSimulator();
		gioco = new DiaDia(simulatore);
	}

	@Test
	void testVittoriaImmediata() {
		String[] istruzioni = { "vai nord" };
		simulatore.setIstruzioni(istruzioni);
		gioco.gioca();
		String[] result = simulatore.getMessaggi();
		assertEquals(result[1], "Biblioteca");
		assertEquals(result[2], "Hai vinto!");
	}

}