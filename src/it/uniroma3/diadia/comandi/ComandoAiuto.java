package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando implements Comando {

	private IO io;

	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("vai, aiuto, prendi, posa, fine, guarda");
	}

	@Override
	public String getNome() {
		return "aiuto";
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}

}
