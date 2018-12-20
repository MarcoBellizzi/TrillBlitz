package prove;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import logic.*;
import util.Util;

public class InvioRichiesta {
	
	public static Scanner input = new Scanner(System.in);
	
	public static Utente utenteCorrente;
	
	public static void main(String[] args) {
		
		Util.initConnection();

		utenteCorrente = new Utente();
		utenteCorrente.effettuaLogIn();
		if(utenteCorrente.getNome().equals("") || utenteCorrente.getPassword().equals("")) {
			System.out.println("vuoi registrarti? ");
			String s = input.nextLine();
			if(s.equals("si")) {
				RegistrazioneUtente.main(args);
			}
			else {
				System.out.println("fottiti!");
				return;
			}
		}
		
		System.out.println("CREAZIONE DI UNA RICHIESTA");
		System.out.println("inserire il luogo in cui fare l'evento");
		String luogo = input.nextLine();
		
		System.out.println("inserire la data nel formato   int anno / int(1/12) mese / int giorno");
		int anno = input.nextInt();
		int mese = input.nextInt()-1;
		int giorno = input.nextInt();
		
		Calendar calendario = Calendar.getInstance();
		calendario.set(anno,mese,giorno);
		Date data = new Date(calendario.getTimeInMillis());
		
		System.out.println("inserire il numero dei partecipanti");
		int n = input.nextInt();
		
		ArrayList<String> listaPartecipanti = new ArrayList<String>();
		String s = input.nextLine();
		for(int i=0; i<n; i++) {
			System.out.println("inserire il nome del partecipante " + i+1 );
			s = input.nextLine();
			listaPartecipanti.add(s);
		}

		
		Richiesta richiesta = new Richiesta(1, utenteCorrente.getNome(),luogo,data,listaPartecipanti);      
		
		if(richiesta.controlla()) {
			System.out.println("richiesta inviata");
			richiesta.save();
		}

		try {
			Util.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
