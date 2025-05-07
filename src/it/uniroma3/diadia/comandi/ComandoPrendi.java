package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPrendi implements Comando {

	String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {

		if (nomeAttrezzo == null) {
			System.out.println("Prendere cosa??");
			return;
		}
		Stanza curr = partita.getLabirinto().getStanzaCorrente();
		Giocatore g = partita.getGiocatore();

		if (curr.hasAttrezzo(nomeAttrezzo)) {
			Attrezzo a = curr.getAttrezzo(nomeAttrezzo);
			g.getBorsa().addAttrezzo(a);
			curr.removeAttrezzo(a);
		} else {
			System.out.println(nomeAttrezzo + " non e' presente nella stanza!");
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

}
