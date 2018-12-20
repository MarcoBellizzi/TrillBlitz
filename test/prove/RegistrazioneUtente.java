package prove;

import java.util.Scanner;

import logic.Utente;
import util.Util;

public class RegistrazioneUtente {

	public static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Util.initConnection();
		
		System.out.println("REGISTRAZIONE");
		System.out.println("inserisci il tuo nickname");
		String nome = input.nextLine();
		
		for(Utente utente : Utente.findAll()) {
			if(utente.getNome().equals(nome)) {
				System.out.println("nickname già presente");
				return;
			}
		}
		
		System.out.println("inserire la password");
		String password = input.nextLine();
		
		Utente utente = new Utente(nome,password);
		utente.save();
		System.out.println("utente registrato\n\n");
		
		System.out.println("ecco la lista degli utenti");
		for(Utente utente_r : Utente.findAll()) {
			System.out.println(utente_r.getNome());
		}
		
		
	}

}
