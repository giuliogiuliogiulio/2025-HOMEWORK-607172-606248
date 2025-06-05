package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Cane extends AbstractPersonaggio {
	
	String ciboPreferito;
	
	Attrezzo regalo;
	
	public Cane(String nome, String presentazione, String ciboPreferito) {
		super(nome, presentazione);
		this.ciboPreferito = ciboPreferito;
		this.regalo = null;
	}

	@Override
	public String agisci(Partita partita) {
		return "woof!";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo.getNome().equals(ciboPreferito)) {
			if (regalo != null) {
				partita.getStanzaCorrente().addAttrezzo(attrezzo);
				return "grazie! squisito! tieni questo...";
			} else {
				return "non ho niente da darti, bau!";
			}
		}
		Giocatore g = partita.getGiocatore();
		g.setCfu(g.getCfu() - 1);
		return "grrrr ora ti mordo!";
	}

}
