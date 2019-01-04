package prove;

import java.sql.SQLException;
import java.util.Scanner;

import logic.Luogo;
import util.Util;

public class AccettazioneRichiesta {

	public static Scanner input = new Scanner(System.in);

	public static Luogo luogoCorrente;

	public static void main(String[] args) {

		Util.initConnection();

		System.out.println("LOG IN");
		System.out.println("inserire il nome");
		String nome = input.nextLine();
		System.out.println("inserire la password");
		String password = input.nextLine();
		
		luogoCorrente = new Luogo();
		
		luogoCorrente.accedi(nome,password);
		
		luogoCorrente.verifica();
		
		luogoCorrente.visualizzaRichieste(); 
		
		
		// notifica gente
		
		try {
			Util.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
