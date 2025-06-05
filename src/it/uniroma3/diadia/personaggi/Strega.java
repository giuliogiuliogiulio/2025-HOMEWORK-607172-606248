package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	
	public Strega(String nome, String presentazione) {
		super (nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		return "portami qualcosa o sparisci...";
	}
		
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo == null) {
			return "pensavi di fregarmi? beh ci sei riuscito alla grande...";
		} else {
			return "HAHGAHGAHAHAHAHHA :D";
		}
	}

}
