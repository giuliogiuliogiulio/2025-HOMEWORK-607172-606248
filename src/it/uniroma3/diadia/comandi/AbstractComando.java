package it.uniroma3.diadia.comandi;

public abstract class AbstractComando implements Comando {

	@Override
	public void setParametro(String parametro) {
		return;
	}

	@Override
	public String getParametro() {
		return null;
	}
}
