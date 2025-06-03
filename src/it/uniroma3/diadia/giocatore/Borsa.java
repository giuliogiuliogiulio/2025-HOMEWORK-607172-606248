package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private ArrayList<Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<>(); // speriamo bastino...
	}
	

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		return this.attrezzi.add(attrezzo);
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (Attrezzo a : attrezzi)
			if (a.getNome().equals(nomeAttrezzo))
				return a;

		return null;
	}

	public int getPeso() {
		int peso = 0;
		for (Attrezzo a : attrezzi) {
			peso += a.getPeso();
		}
		return peso;
	}

	public boolean isEmpty() {
		return this.attrezzi.size() == 0;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo res = null;
		
		for (Attrezzo a : attrezzi) {
			if (a.getNome().equals(nomeAttrezzo)) {
				attrezzi.remove(a);
				res = a;
				break;
			}
		}
		
		return res;	
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		this.attrezzi.sort(new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				if (a1.getPeso() < a2.getPeso()) {
					return -1;
				} else if (a1.getPeso() > a2.getPeso()) {
					return 1;
				} else {
					return a1.getNome().compareTo(a2.getNome());
				}
			}
		});
		
		return this.attrezzi;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		TreeSet<Attrezzo> res = new TreeSet<>(new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				return a1.getNome().compareTo(a2.getNome());
			}
		});
		
		res.addAll(this.attrezzi);
		
		return res;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		HashMap<Integer, Set<Attrezzo>> res = new HashMap<>();
		
		for (Attrezzo a : attrezzi) {
			int peso = a.getPeso();
			if (res.containsKey(peso)) {
				res.get(peso).add(a);
			} else {
				Set<Attrezzo> s = new HashSet<Attrezzo>();
				s.add(a);
				res.put(peso, s);
			}
		}
		
		return res;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			s.append('\n');
			s.append(getContenutoOrdinatoPerPeso().toString());
		} else
			s.append("Borsa vuota");
		return s.toString();
	}
}
