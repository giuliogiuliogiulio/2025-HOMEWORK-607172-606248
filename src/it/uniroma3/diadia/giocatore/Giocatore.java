package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.ConfigSingleton;

public class Giocatore {

	private Borsa borsa;

	int cfu;

	public Giocatore() {
		this.borsa = new Borsa();
		this.cfu = ConfigSingleton.getCfuIniziali();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append("Numero cfu giocatore: " + this.cfu);
		str.append(" \n");
		str.append(this.borsa.toString());

		return str.toString();
	}

	public Borsa getBorsa() {
		return this.borsa;
	}

	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

}
