package it.uniroma3.diadia.ambienti;

import java.util.Map;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	
	private Map<String, Stanza> graph;
	
	Stanza ultimaStanzaAggiunta, vincente, iniziale;
	
	public LabirintoBuilder() {
		this.graph = new HashMap<>();
	}
	
	public LabirintoBuilder addStanza(Stanza s) {
		this.graph.put(s.getNome(), s);
		this.ultimaStanzaAggiunta = s;
		return this;
	}
	
	public LabirintoBuilder addStanza(String nomeStanza) {
		return addStanza(new Stanza(nomeStanza));
	}
	
	public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
		if (! this.graph.containsKey(nomeStanza)) {
			Stanza s = new Stanza(nomeStanza);
			addStanza(s);	
			this.iniziale = s;
		} else {
			this.iniziale = graph.get(nomeStanza);
		}
		
		return this;
	}
	
	// se nomeStanza è già presente nel labirinto viene impostata come vincente.
	// se nomeStanza non è presente nel labirinto, una nuova stanza nomeStanza
	// viene aggiunta al labirinto e impostata come vincente
	public LabirintoBuilder addStanzaVincente(String nomeStanza) {
		if (! this.graph.containsKey(nomeStanza)) {
			Stanza s = new Stanza(nomeStanza);
			addStanza(s);	
			this.vincente = s;
		} else {
			this.vincente = graph.get(nomeStanza);
		}
		
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(Attrezzo attrezzo) {
		this.ultimaStanzaAggiunta.addAttrezzo(attrezzo);
		return this;
	}
	
	// Overload
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
		Attrezzo a = new Attrezzo(nomeAttrezzo, peso);
		return this.addAttrezzo(a);
	}

	// addAdiacenza non aggiunge nuove stanze, quello è il compito di addStanza
	// addAdiacenze le solo connette
	public LabirintoBuilder addAdiacenza(String from, String to, String direzione) {
		Stanza source = this.graph.get(from);
		Stanza target = this.graph.get(to);
		source.impostaStanzaAdiacente(direzione, target);
		return this;
	}
	
	public Labirinto getLabirinto() {
		return new Labirinto(iniziale, vincente);
	}

	public LabirintoBuilder addStanzaBloccata(String nomeStanza, String chiave, String direzioneChiave) {
		Stanza bl = new StanzaBloccata(nomeStanza, chiave, direzioneChiave);
		addStanza(bl);
		return this;
	}

	public Map<String, Stanza> getListaStanze() {
		return this.graph;
	}

	public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int sogliaMagica) {
		Stanza m = new StanzaMagica(nomeStanzaMagica, sogliaMagica);
		addStanza(m);
		return this;
	}

	public LabirintoBuilder addStanzaBuia(String nomeStanza, String attrezzo) {
		Stanza bu = new StanzaBuia(nomeStanza, attrezzo);
		addStanza(bu);
		return this;
	}
	
	public LabirintoBuilder addAttrezzoToStanza(String nomeStanza, Attrezzo a) {
		this.graph.get(nomeStanza).addAttrezzo(a);
		return this;
	}
	
	//non chained methods:
	
	public boolean isStanzaPresente(String nomeStanza) {
		return graph.containsKey(nomeStanza);
	}

} 