package prove;

import java.sql.SQLException;
import java.util.Scanner;

import logic.Luogo;
import logic.Richiesta;
import util.Util;

public class AccettazioneRichiesta {

	public static Scanner input = new Scanner(System.in);

	public static Luogo luogoCorrente;

	public static void main(String[] args) {

		Util.initConnection();

		luogoCorrente = new Luogo();
		luogoCorrente.effettuaLogIn();
		if(luogoCorrente.getNome().equals("") || luogoCorrente.getPassword().equals(""))
			return;

		boolean almenoUna = false;
		for(Richiesta richiesta : Richiesta.findAll(luogoCorrente.getNome())) {
			almenoUna = true;
			System.out.println("nuova richiesta da : " + richiesta.getCreatore());
			System.out.println("partecipanti proposti ");
			for(String utente : richiesta.getListaPartecipanti()) {
				System.out.println(utente);
			}

			System.out.println("accetti?");
			String s = input.next();

			if(s.equals("si")) {
				richiesta.accetta();    // crea l'evento
				System.out.println("richiesta accettata");
			}
			else {
				System.out.println("richiesta non accettata");
			}
		}

		if(!almenoUna)
			System.out.println("nessuna nuova richiesta");

		try {
			Util.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
