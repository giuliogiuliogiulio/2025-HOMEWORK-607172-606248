package it.uniroma3.diadia;

import java.util.Scanner;

public class IOConsole implements IO {

	Scanner s;

	IOConsole(Scanner s) {
		this.s = s;
	}

	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	public String leggiRiga() {
		return s.nextLine();
		/*
		 * try (Scanner s = new Scanner(System.in)) { return s.nextLine(); } catch
		 * (NoSuchElementException e) { return null; }
		 * 
		 * Scanner scannerDiLinee = new Scanner(System.in); String riga =
		 * scannerDiLinee.nextLine(); // Questa roba va tenuta commentata o crasha
		 * "aiuto"!!!!!!!!!!!!!! // scannerDiLinee.close(); return riga;
		 */
	}

}