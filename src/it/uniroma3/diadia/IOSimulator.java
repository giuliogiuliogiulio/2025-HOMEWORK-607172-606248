package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class IOSimulator implements IO {

	private Stack<String> istruzioni;
	private Queue<String> log;
	private String ultimaIstruzione;

	private Map<String, String> istruzioni2log;

	public IOSimulator() { 
		istruzioni2log = new HashMap<>();
		log = new LinkedList<String>();
		istruzioni = new Stack<String>();
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		if (istruzioni2log.containsKey(ultimaIstruzione)) {
			String curr = istruzioni2log.get(ultimaIstruzione);
			istruzioni2log.replace(ultimaIstruzione, curr + messaggio);
		} else {
			istruzioni2log.put(ultimaIstruzione, messaggio);
		}
		
		log.add(messaggio);
	}

	@Override
	public String leggiRiga() {
		String output = istruzioni.pop();
		ultimaIstruzione = output;
		return output;
	}

	public void setIstruzioni(List<String> i) {
		this.istruzioni.addAll(i);
	}

	public List<String> getMessaggi() {
		List<String> res = new ArrayList<>(log.size());
		res.addAll(log);
		return res;
	}

}
