package it.uniroma3.diadia.giocatore;

public class Giocatore {

    private Borsa borsa;

    static final private int CFU_INIZIALI = 20;
    int cfu;

    public Giocatore() {
		this.borsa = new Borsa();
		this.cfu = CFU_INIZIALI;
    }

    @Override 
    public String toString() {
		StringBuilder str = new StringBuilder();
	
		str.append("Numero cfu giocatore: " + this.cfu);
		str.append(" \n");
		str.append(this.borsa.toString());
	
		return str.toString();
    }

    public Borsa getBorsa() {
    	return this.borsa;
    }

    public int getCfu() {
    	return this.cfu;
    }

    public void setCfu(int cfu) {
    	this.cfu = cfu;		
    }	
    
}
