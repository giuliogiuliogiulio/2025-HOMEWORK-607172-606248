package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando{
	private String direzione;
	
	
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
			System.out.println("Dove vuoi andare? Devi specificare una direzione");
		} 
		
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			System.out.println("Direzione inesistente");
			return;
		}
		
		lab.setStanzaCorrente(prossimaStanza);
		System.out.println(lab.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
	}
}
