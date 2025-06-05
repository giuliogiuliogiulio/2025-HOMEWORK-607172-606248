package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando implements Comando {

	private IO io;

	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio(partita.toString());
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "guarda";
	}
	
	@Override
	public void setIO(IO io) { this.io = io; }

}
