package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

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
		List<String> result = simulatore.getMessaggi();
		assertEquals(result.get(1), "Biblioteca");
		assertEquals(result.get(2), "Hai vinto!");
	}

}