package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.*;

public class StanzaMagica extends Stanza {

	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	static final private int SOGLIA_MAGICA_DEFUALT = 3;

	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFUALT);
	}

	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(), pesoX2);
		return attrezzo;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if (this.contatoreAttrezziPosati > this.sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo);
		return super.addAttrezzo(attrezzo);
	}

}
