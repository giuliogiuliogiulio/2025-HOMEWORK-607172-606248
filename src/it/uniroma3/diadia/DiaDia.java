package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.*;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * 
 * @version base
 */
public class DiaDia {

	private IO console;

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;

	public DiaDia(IO console, Labirinto lab) {
		this.partita = new Partita(lab);
		this.console = console;
	}

	public void gioca() {
		String istruzione;

		console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do
			istruzione = console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva(this.console);

		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta()) {
			console.mostraMessaggio("Hai vinto!");
		}

		return this.partita.isFinita();
	}

	public static void main(String[] argc) throws IOException {
		IO io = new IOConsole();
		DiaDia gioco = null;
		
		try (InputStream input = DiaDia.class.getResourceAsStream("/labirinto.txt")) {
			
			if (input == null) {
				io.mostraMessaggio("labirinto.txt non è stato trovato");
				return;
			}
			
			CaricatoreLabirinto c = new CaricatoreLabirinto(input);
			c.carica();
			Labirinto lab = c.toLabirinto();
			gioco = new DiaDia(io, lab);
		} catch (FormatoFileNonValidoException e) {
			io.mostraMessaggio(e.getMessage());
			return;
		} catch (FileNotFoundException e) {
			io.mostraMessaggio("labirinto.txt non è stato trovato");
			return;
		}

		gioco.gioca();
	}
}
