 package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPosa implements Comando {
	
	private IO io;

	String nomeAttrezzo;

	ComandoPosa(IO io) { this.io = io; }
	
	@Override
	public void esegui(Partita partita) {
		if (nomeAttrezzo == null) {
			io.mostraMessaggio("Posare cosa?");
			return;
		}

		Stanza curr = partita.getLabirinto().getStanzaCorrente();
		Giocatore g = partita.getGiocatore();

		if (g.getBorsa().hasAttrezzo(nomeAttrezzo)) {
			Attrezzo a = g.getBorsa().getAttrezzo(nomeAttrezzo);
			if (curr.addAttrezzo(a)) {
				g.getBorsa().removeAttrezzo(nomeAttrezzo);
			} else {
				io.mostraMessaggio("la stanza è piena!");
			}
		} else {
			io.mostraMessaggio(nomeAttrezzo + " non e' presente nella tua borsa!");
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

}
