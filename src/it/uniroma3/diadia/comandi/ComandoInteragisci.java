package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoInteragisci extends AbstractComando {

	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		Stanza curr = partita.getStanzaCorrente();
		if (curr.hasPersonaggio()) {
			io.mostraMessaggio(curr.getPersonaggio().agisci(partita));
		} else {
			io.mostraMessaggio("non c'è nessuno con cui interagire qui...");
		}
		
	}

	@Override
	public String getNome() {
		return "interagisci";
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}

}
