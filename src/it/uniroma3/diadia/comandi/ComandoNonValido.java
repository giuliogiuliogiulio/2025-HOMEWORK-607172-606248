package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {

	String parametro;

	private IO io;

	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Comando non valido!!");
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public String getNome() {
		return "non valido";
	}

	@Override
	public String getParametro() {
		return parametro;
	}
	
	@Override
	public void setIO(IO io) { this.io = io; }

}
