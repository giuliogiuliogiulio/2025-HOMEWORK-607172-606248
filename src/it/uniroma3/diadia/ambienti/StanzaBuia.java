package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	
	String attrezzoLuce;
	
	public StanzaBuia(String nome, String attrezzoLuce) {
		super(nome);
		this.attrezzoLuce = attrezzoLuce;
	}
	
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(attrezzoLuce)) {
			return super.getDescrizione();
		}
		else
			return "qui c'è buio pesto";
	}
}