package it.uniroma3.diadia.giocatore;

import java.util.*;

import it.uniroma3.diadia.ConfigSingleton;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	private Map<String, Attrezzo> nome2attrezzi;
	private int pesoMax;

	public Borsa() {
		this(ConfigSingleton.getPesoMaxBorsa());
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.nome2attrezzi = new HashMap<>();
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (nome2attrezzi.containsKey(attrezzo.getNome()))
			return false;
		nome2attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return nome2attrezzi.get(nomeAttrezzo);
	}

	public int getPeso() {
		Collection<Attrezzo> attrezzi = nome2attrezzi.values();
		int peso = 0;
		for (Attrezzo a : attrezzi) {
			peso += a.getPeso();
		}
		return peso;
	}

	public boolean isEmpty() {
		return this.nome2attrezzi.size() == 0;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return nome2attrezzi.remove(nomeAttrezzo);
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		ArrayList<Attrezzo> attrezzi = new ArrayList<>(nome2attrezzi.size());
		attrezzi.addAll(nome2attrezzi.values());
		attrezzi.sort(new Comparator<Attrezzo>() {
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

		return attrezzi;
	}

	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		TreeSet<Attrezzo> res = new TreeSet<>(new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				return a1.getNome().compareTo(a2.getNome());
			}
		});
		res.addAll(nome2attrezzi.values());

		return res;
	}

	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		HashMap<Integer, Set<Attrezzo>> res = new HashMap<>();

		for (Attrezzo a : nome2attrezzi.values()) {
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

	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		TreeSet<Attrezzo> res = new TreeSet<>(new Comparator<Attrezzo>() {
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

		if (res.addAll(nome2attrezzi.values())) {
			return res;
		} else {
			return null;
		}
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
