package it.uniroma3.diadia.comandi;

public class ComandoVai implements Comando{
	private String direzione;
	
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
	
	public void esegui(Partita partita) {
		
	}
}
