package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	private IO io;

	public FabbricaDiComandiRiflessiva(IO io) {
		this.io = io;
	}

	@Override
	public Comando costruisciComando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();// prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();// seconda parola: eventuale parametro
		try {
			String nomeClasse = "it.uniroma3.diadia.comandi.Comando";
			nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
			nomeClasse += nomeComando.substring(1);
			// eclipse consiglia di aggiungere getDeclaredConstructor() prima di
			// newInstance(). dare newInstance() direttamente è deprecato. Per eseguire
			// newInstance() direttamente lo stesso aggiungere l'annotazione
			// @SuppressWarnings("deprecation")
			// al metodo costruisciComando
			comando = (Comando) Class.forName(nomeClasse).getDeclaredConstructor().newInstance();
			comando.setParametro(parametro);
		} catch (Exception e) {
			comando = new ComandoNonValido();
			this.io.mostraMessaggio("Comando inesistente");
		}
		comando.setIO(io);
		scannerDiParole.close();
		return comando;
	}
}