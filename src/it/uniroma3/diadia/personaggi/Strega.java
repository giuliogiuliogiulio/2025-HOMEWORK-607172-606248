package it.uniroma3.diadia.personaggi;

import java.util.Collections;
import java.util.Comparator;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		Stanza curr = partita.getStanzaCorrente();

		Comparator<Stanza> comparatoreStanze = new Comparator<Stanza>() {
			@Override
			public int compare(Stanza s1, Stanza s2) {
				return Integer.compare(s1.getAttrezzi().size(), s2.getAttrezzi().size());
			}
		};

		String message = null;
		Stanza target = null;
		if (haSalutato()) {
			target = Collections.max(curr.getMapStanzeAdiacenti().values(), comparatoreStanze);
			message = "sei stato gentile, ti porto in" + target.getNome();
		} else {
			target = Collections.min(curr.getMapStanzeAdiacenti().values(), comparatoreStanze);
			message = "questi giovani si sono dimenticati le buone maniere";
		}

		partita.getLabirinto().setStanzaCorrente(target);
		return message;
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
