package it.uniroma3.diadia.ambienti;

import java.util.*;
import java.util.stream.Collectors;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo. Una stanza e' un luogo
 * fisico nel gioco. E' collegata ad altre stanze attraverso delle uscite. Ogni
 * uscita e' associata ad una direzione.
 * 
 * @author docente di POO
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	private String nome;
	
	private AbstractPersonaggio p;

	private Map<String, Attrezzo> nome2attrezzi;

	private Map<Direzione, Stanza> direzione2stanza;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * 
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.direzione2stanza = new EnumMap<Direzione,Stanza>(Direzione.class);
		this.nome2attrezzi = new HashMap<String,Attrezzo>();
		this.p = null;
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza    stanza adiacente nella direzione indicata dal primo
	 *                  parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		try {
			Direzione d = Direzione.valueOf(direzione);	
			direzione2stanza.put(d, stanza);
		} catch (IllegalArgumentException e) {
			return;
		}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		try {
			Direzione d = Direzione.valueOf(direzione);
			return direzione2stanza.get(d);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	/**
	 * Restituisce la nome della stanza.
	 * 
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * 
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * 
	 * @return la collezione di attrezzi nella stanza.
	 */
	public List<Attrezzo> getAttrezzi() {
		ArrayList<Attrezzo> res = new ArrayList<>();
		res.addAll(nome2attrezzi.values());
		return res;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * 
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(nome2attrezzi.containsKey(attrezzo.getNome()))
			return false;
		nome2attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza, stampadone la
	 * descrizione, le uscite e gli eventuali attrezzi contenuti
	 * 
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (Direzione d : direzione2stanza.keySet()) {
			risultato.append(" " + d);
		}
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.getAttrezzi()) {
			risultato.append(attrezzo.toString() + " ");
		}
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.nome2attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * 
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza. null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.nome2attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * 
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {	
		return this.nome2attrezzi.remove(attrezzo.getNome(), attrezzo);
	}
	
	@Override
	public boolean equals(Object o) {
		Stanza that = (Stanza) o;
		return this.nome.equals(that.getNome());
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}

	public List<String> getDirezioni() {
		return direzione2stanza
				.keySet()
				.stream()
				.map(Direzione::toString)
				.collect(Collectors.toList());
	}

	public Map<String, Stanza> getMapStanzeAdiacenti() {
		// soluzione brutta veloce
		HashMap<String, Stanza> r = new HashMap<>();
		direzione2stanza.forEach((d, s) -> {
			r.put(d.toString(), s);
		});
		return r;
	}

	public boolean hasPersonaggio() {
		return p != null;
	}
	
	public AbstractPersonaggio getPersonaggio() {
		return p;
	}

}
