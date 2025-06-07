package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Cane extends AbstractPersonaggio {

	private static final String ciboPreferito = "osso";

	Attrezzo regalo;

	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
		this.regalo = null;
	}

	@Override
	public String agisci(Partita partita) {
		Giocatore g = partita.getGiocatore();
		g.setCfu(g.getCfu() - 1);
		return "grrrr";
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
		return agisci(partita);
	}

}
