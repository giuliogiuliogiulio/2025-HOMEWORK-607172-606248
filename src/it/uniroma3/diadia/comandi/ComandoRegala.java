package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoRegala implements Comando {

	private IO io;

	String parametro;

	@Override
	public void esegui(Partita partita) {
		Stanza curr = partita.getStanzaCorrente();

		if (!curr.hasPersonaggio()) {
			io.mostraMessaggio("non c'è nessuno a cui regalare nulla qui...");
			return;
		}

		Giocatore g = partita.getGiocatore();
		if (g.getBorsa().hasAttrezzo(parametro)) {
			Attrezzo a = g.getBorsa().getAttrezzo(parametro);
			g.getBorsa().removeAttrezzo(parametro);

			curr.getPersonaggio().riceviRegalo(a, partita);
		} else {
			io.mostraMessaggio(parametro + "non è nella tua borsa");
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public String getNome() {
		return "regala";
	}

	@Override
	public String getParametro() {
		return parametro;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}

}
