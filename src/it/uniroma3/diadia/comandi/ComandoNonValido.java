package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {

	String parametro;

	private IO io;

	ComandoNonValido(IO io) {
		this.io = io;
	}

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
		// TODO Auto-generated method stub
		return "non valido";
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return parametro;
	}

}
