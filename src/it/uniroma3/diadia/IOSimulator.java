package it.uniroma3.diadia;

public class IOSimulator implements IO {

	private String[] istruzioni;
	private int currentIstruzione = 0;

	private String[] log;
	private int currentLog = 0;

	public IOSimulator() {
		this.log = new String[20];
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		if (log.length == currentLog) {
			String[] lognew = new String[log.length * 2];
			for (int i = 0; i < log.length; i++) {
				lognew[i] = log[i];
			}
			this.log = lognew;
		}
		log[currentLog] = messaggio;
		currentLog++;
	}

	@Override
	public String leggiRiga() {
		if (currentIstruzione == istruzioni.length) {
			return "";
		}
		String output = istruzioni[currentIstruzione];
		currentIstruzione--;
		return output;
	}

	public void setIstruzioni(String[] s) {
		this.istruzioni = s;
	}

	public String[] getMessaggi() {
		return this.log;
	}

}
