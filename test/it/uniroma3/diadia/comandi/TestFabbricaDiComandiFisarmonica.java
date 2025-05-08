package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;

class TestFabbricaDiComandiFisarmonica {
	
	FabbricaDiComandiFisarmonica f;

	@BeforeEach
	void setUp() throws Exception {
		f = new FabbricaDiComandiFisarmonica(new IOConsole());
	}

	@Test
	void testNonValido() {
		Comando result = f.costruisciComando("slfhdsoblf fdsfsdf");
		assertEquals(result.getNome(), "non valido");
	}
	
	@Test
	void testSwitchCase() {
		String[] comandi = {
				"posa x",
				"prendi x",
				"aiuto",
				"fine",
				"guarda"
		};
		
		for (String c : comandi) {
			Scanner s = new Scanner(c);
			Comando result = f.costruisciComando(c);
			assertEquals(result.getNome(), s.next());
			if (s.hasNext()) {
				assertEquals(result.getParametro(), s.next());
			}
			s.close();
		}
	}

}
