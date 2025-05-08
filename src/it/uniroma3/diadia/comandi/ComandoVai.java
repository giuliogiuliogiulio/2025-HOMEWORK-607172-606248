package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {
	
	private IO io;
	
	private String direzione;

	ComandoVai(IO io) { this.io = io; }
	
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

	@Override
	public void esegui(Partita partita) {
		Labirinto lab = partita.getLabirinto();
		Stanza stanzaCorrente = lab.getStanzaCorrente();
		Stanza prossimaStanza = null;

		if (this.direzione == null) {
			io.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
		}

		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			io.mostraMessaggio("Direzione inesistente");
			return;
		}

		lab.setStanzaCorrente(prossimaStanza);
		io.mostraMessaggio(lab.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "vai";
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return direzione;
	}
}
