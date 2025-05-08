package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	
	private String direzioneBloccata;
	private String nomeAttrChiave;

	public StanzaBloccata(String nome, String dirBloccata, String attr) {
		super(nome);
		this.direzioneBloccata = dirBloccata;
		this.nomeAttrChiave = attr;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String dir) {
		if (dir.equals(direzioneBloccata) && !this.hasAttrezzo(nomeAttrChiave)) {
			return this;
		} else {
			return super.getStanzaAdiacente(dir);
		}
	}
	
	@Override
	public String getDescrizione() {
		return super.getDescrizione() + '\n' + direzioneBloccata + "richiede attrezzo: " + nomeAttrChiave;
	}

}
