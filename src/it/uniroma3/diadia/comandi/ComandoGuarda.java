package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	
	private IO io;

	ComandoGuarda(IO io) { this.io = io; }
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio(partita.toString());
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

}
