package prove;

import java.sql.SQLException;
import java.util.Scanner;

import logica.Locale;
import util.Connessione;

public class AccettazioneRichiesta {

	public static Scanner input = new Scanner(System.in);

	public static Locale localeCorrente;

	public static void main(String[] args) {

		Connessione.initConnection();

		System.out.println("LOG IN");
		System.out.println("inserire il nome");
		String nome = input.nextLine();
		System.out.println("inserire la password");
		String password = input.nextLine();
		
		localeCorrente = new Locale();
		
		localeCorrente.accedi(nome,password);
		
		localeCorrente.verifica();
		
		localeCorrente.visualizzaRichieste(); 
		
		// notifica gente
		
		try {
			Connessione.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
