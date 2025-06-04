package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IOSimulator implements IO {

	// l'ideale per istruzioni sarebbe implementare una queue e per log una stack
	private List<String> istruzioni, log;
	private String ultimaIstruzione;

	private Map<String, String> istruzioni2log;

	public IOSimulator() { 
		istruzioni2log = new HashMap<>();
		log = new ArrayList<>();
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		if (ultimaIstruzione != null) {
			istruzioni2log.put(ultimaIstruzione, messaggio);
			ultimaIstruzione = null;
		}
		
		log.add(messaggio);
	}

	@Override
	public String leggiRiga() {
		String output = istruzioni.getFirst();
		ultimaIstruzione = output;
		istruzioni.removeFirst();
		return output;
	}

	public void setIstruzioni(String[] i) {
		this.istruzioni = new LinkedList<>(Arrays.asList(i));
	}

	public List<String> getMessaggi() {
		return this.log;
	}

}
