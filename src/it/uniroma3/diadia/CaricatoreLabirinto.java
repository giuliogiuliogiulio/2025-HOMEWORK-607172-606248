package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {
	
	private LabirintoBuilder builder;

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze: ";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio: ";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente: ";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi: ";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite: ";
	
	private static final String MAGICHE_MARKER = "Magiche: ";
	
	private static final String BUIE_MARKER = "Buie: ";
	
	private static final String BLOCCATE_MARKER = "Bloccate: ";
	
	private static final String MAGHI_MARKER = "Maghi: ";
	
	private static final String CANI_MARKER = "Cani: ";
	
	private static final String STREGHE_MARKER = "Streghe: ";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	
	private LineNumberReader reader;
	
	public CaricatoreLabirinto(FileReader file) {
		this.reader = new LineNumberReader(file);
		this.builder = Labirinto.newBuilder();
	}

	public CaricatoreLabirinto(String contenuti) {
		this.reader = new LineNumberReader(new StringReader(contenuti));
		this.builder = Labirinto.newBuilder();
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaMagiche();
			this.leggiECreaBuie();
			this.leggiECreaBloccate();
			this.leggiInizialeEvincente();
			this.aggiungiMaghi();
			this.aggiungiCani();
			this.aggiungiStreghe();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			this.builder.addStanza(nomeStanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(", ");
		try (Scanner scannerDiParole = scanner) {
			while (scannerDiParole.hasNext())
				result.add(scannerDiParole.next());
		}
		return result;
	}

	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale + " non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.builder.addStanzaVincente(nomeStanzaVincente);
		this.builder.addStanzaIniziale(nomeStanzaIniziale);
	}	
	
	private void leggiECreaMagiche() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(MAGICHE_MARKER);
		
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			this.builder.addStanza(nomeStanza);
		}
	}
	
	private void leggiECreaBuie() throws FormatoFileNonValidoException {
		String specificheBuie= this.leggiRigaCheCominciaPer(BUIE_MARKER);
		
		for(String specificaBuia : separaStringheAlleVirgole(specificheBuie)) {
			String nomeStanza = null;
			String nomeAttrezzo = null;
			try (Scanner scannerLinea = new Scanner(specificaBuia)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza buia."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo di una stanza buia "+nomeStanza+"."));
				nomeAttrezzo = scannerLinea.next();
			}				
			this.builder.addStanza(new StanzaBuia(nomeStanza, nomeAttrezzo));
		}
	}
	
	private void leggiECreaBloccate() throws FormatoFileNonValidoException {
		String specificheBloccate= this.leggiRigaCheCominciaPer(BLOCCATE_MARKER);
		
		for(String specificaBloccata : separaStringheAlleVirgole(specificheBloccate)) {
			String nomeStanza = null;
			String nomeAttrezzo = null;
			String direzioneBloccata = null;
			try (Scanner scannerLinea = new Scanner(specificaBloccata)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza bloccata."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la chiave di una stanza bloccata "+nomeStanza+"."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione bloccata "+nomeStanza+"."));
				direzioneBloccata = scannerLinea.next();
			}				
			this.builder.addStanza(new StanzaBloccata(nomeStanza, nomeAttrezzo, direzioneBloccata));
		}
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}
	
	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.builder.addAttrezzoToStanza(nomeStanza, attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}
	
	private void aggiungiMaghi() throws FormatoFileNonValidoException {
		String specificheMaghi = this.leggiRigaCheCominciaPer(MAGHI_MARKER);

		for(String specificaMago : separaStringheAlleVirgole(specificheMaghi)) {
			String nome = null;
			String presentazione = null;
			String nomeStanza = null; 
			String attrezzo = null;
			try (Scanner scannerLinea = new Scanner(specificaMago)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un mago."));
				nome = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("presentazione del mago "+nome+"."));
				presentazione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il mago"+nome+"."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("attrezzo di un mago "+nome+"."));
				attrezzo = scannerLinea.next();
			}
			this.builder.addPersonaggioToStanza(nomeStanza, new Mago(nome, presentazione, new Attrezzo(attrezzo, 1)));
		}
	}
	
	private void aggiungiCani() throws FormatoFileNonValidoException {
		String specificheCani = this.leggiRigaCheCominciaPer(CANI_MARKER);

		for(String specificaCane : separaStringheAlleVirgole(specificheCani)) {
			String nome = null;
			String presentazione = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaCane)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un cane."));
				nome = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("presentazione del cane"+nome+"."));
				presentazione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il mago"+nome+"."));
				nomeStanza = scannerLinea.next();
			}
			this.builder.addPersonaggioToStanza(nomeStanza, new Cane(nome, presentazione));
		}
	}
	
	private void aggiungiStreghe() throws FormatoFileNonValidoException {
		String specificheStreghe = this.leggiRigaCheCominciaPer(STREGHE_MARKER);

		for(String specificaStrega : separaStringheAlleVirgole(specificheStreghe)) {
			String nome = null;
			String presentazione = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaStrega)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una strega."));
				nome = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("presentazione della strega"+nome+"."));
				presentazione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare la strega"+nome+"."));
				nomeStanza = scannerLinea.next();
			}
			this.builder.addPersonaggioToStanza(nomeStanza, new Strega(nome, presentazione));
		}
	}

	private boolean isStanzaValida(String nomeStanza) {
		return this.builder.isStanzaPresente(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for (String specificaUscita : separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(specificaUscita)) {			
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();
				
					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			}
		}
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere " + msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+ stanzaDa);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ nomeA);
		this.builder.addAdiacenza(stanzaDa, nomeA, dir);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}
	
	public Labirinto toLabirinto() {
		return this.builder.getLabirinto();
	}

	public Stanza getStanzaIniziale() {
		return this.builder.getLabirinto().getStanzaIniziale();
	}

	public Stanza getStanzaVincente() {
		return this.builder.getLabirinto().getStanzaVincente();
	}
}
