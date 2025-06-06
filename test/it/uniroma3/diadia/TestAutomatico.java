package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class TestAutomatico {

	IOSimulator simulatore;
	DiaDia gioco;
	Labirinto immediato;

	@BeforeEach
	void setUp() throws Exception {
		LabirintoBuilder builder = new LabirintoBuilder();
		builder
		.addStanza("atrio")
		.addStanza("biblioteca")
		.addAdiacenza("atrio", "biblioteca", "nord")
		.addStanzaIniziale("atrio")
		.addStanzaVincente("biblioteca");
		simulatore = new IOSimulator();
		gioco = new DiaDia(simulatore, builder.getLabirinto());
	}

	@Test
	void testVittoriaImmediata() {
		List<String> istruzioni = Collections.singletonList("vai nord");
		simulatore.setIstruzioni(istruzioni);
		gioco.gioca();
		List<String> result = simulatore.getMessaggi();
		assertEquals(result.get(1), "biblioteca");
		assertEquals(result.get(2), "Hai vinto!");
	}

}