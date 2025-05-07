package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPosa implements Comando {

	String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		if (nomeAttrezzo == null) {
			System.out.println("Posare cosa?");
			return;
		}

		Stanza curr = partita.getLabirinto().getStanzaCorrente();
		Giocatore g = partita.getGiocatore();

		if (g.getBorsa().hasAttrezzo(nomeAttrezzo)) {
			Attrezzo a = g.getBorsa().getAttrezzo(nomeAttrezzo);
			curr.addAttrezzo(a);
			g.getBorsa().removeAttrezzo(nomeAttrezzo);
		} else {
			System.out.println(nomeAttrezzo + " non e' presente nella tua borsa!");
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

}
