package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	
	private IO io;
	
	ComandoAiuto(IO io) {
		this.io = io;
	}

	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("vai, aiuto, prendi, posa, fine, guarda");
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public String getParametro() { return null; }
	
	@Override
	public String getNome() { return "aiuto"; }

}
