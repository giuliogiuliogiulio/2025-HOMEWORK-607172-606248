package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private boolean finita;

	private Giocatore giocatore;
	private Labirinto lab;
	
	public Partita(Labirinto lab) {
		this.lab = lab;
		
		this.finita = false;
		this.giocatore = new Giocatore();
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * 
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.lab.getStanzaCorrente() == this.lab.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * 
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public String toString() {
		return this.lab.getStanzaCorrente().getDescrizione() + "\nCfu = " + this.getGiocatore().getCfu();
	}

	public Giocatore getGiocatore() {
		return this.giocatore;
	}

	public Labirinto getLabirinto() {
		return this.lab;
	}
	
	public void setLabirinto(Labirinto lab) {
		this.lab = lab;
	}

	public Stanza getStanzaCorrente() {
		return this.lab.getStanzaCorrente();
	}

}
