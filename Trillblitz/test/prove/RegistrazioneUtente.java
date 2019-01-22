package prove;

import java.util.Scanner;

import logica.Musicista;
import util.Connessione;

public class RegistrazioneUtente {

	public static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Connessione.initConnection();
		
		System.out.println("REGISTRAZIONE");
		System.out.println("inserisci il tuo nickname");
		String nome = input.nextLine();
		
		for(Musicista utente : Musicista.findAll()) {
			if(utente.getNome().equals(nome)) {
				System.out.println("nickname già presente");
				return;
			}
		}
		
		System.out.println("inserire la password");
		String password = input.nextLine();
		
		Musicista utente = new Musicista(nome,password,"");
		utente.save();
		System.out.println("utente registrato\n\n");
		
		System.out.println("ecco la lista degli utenti");
		for(Musicista utente_r : Musicista.findAll()) {
			System.out.println(utente_r.getNome());
		}
		
		
	}

}
