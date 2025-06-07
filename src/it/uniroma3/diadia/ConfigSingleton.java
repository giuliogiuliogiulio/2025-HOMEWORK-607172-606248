package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// singleton che gestisce il file di configurazione esterno
// altrimenti il refactoring diventa complicato. 
public class ConfigSingleton {
	private static final Properties p = new Properties();

	static {
		try (InputStream input = ConfigSingleton.class.getResourceAsStream("/diadia.properties")) {
			if (input == null) {
				throw new RuntimeException("Errore nel caricamento del file diadia.properties");
			}
			p.load(input);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Errore nel caricamento del file config.properties", e);
		} catch (IOException e) {
			throw new RuntimeException("Errore nel caricamento del file config.properties", e);
		}
	}

	public static int getCfuIniziali() {
		String i = p.getProperty("cfu_iniziali");
		return Integer.parseInt(i);
	}

	public static int getPesoMaxBorsa() {
		String i = p.getProperty("peso_max_borsa");
		return Integer.parseInt(i);
	}

}
