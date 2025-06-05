package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoSaluta extends AbstractComando {
	
	private IO io;

	@Override
	public void esegui(Partita partita) {
		Stanza curr = partita.getStanzaCorrente();
		if (curr.hasPersonaggio()) {
			io.mostraMessaggio(curr.getPersonaggio().saluta());
		} else {
			io.mostraMessaggio("qui non c'è nessuno...");
		}
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}

}
