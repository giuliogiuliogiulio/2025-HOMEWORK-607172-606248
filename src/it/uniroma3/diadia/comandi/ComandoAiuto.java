package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {

	@Override
	public void esegui(Partita partita) {
		System.out.print("vai, aiuto, prendi, posa, fine, guarda");
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

}
