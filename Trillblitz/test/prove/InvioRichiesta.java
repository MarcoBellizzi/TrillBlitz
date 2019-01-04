package prove;

import java.sql.SQLException;
import java.util.Scanner;

import logic.Richiesta;
import logic.Utente;
import util.Util;

public class InvioRichiesta {
	
	public static Scanner input = new Scanner(System.in);
	
	public static Utente utenteCorrente;
	
	public static void main(String[] args) {
		
		Util.initConnection();
		
		System.out.println("LOG IN");
		System.out.println("inserire il nome");
		String nome = input.nextLine();
		System.out.println("inserire la password");
		String password = input.nextLine();
		
		utenteCorrente = new Utente();
		
		utenteCorrente.accedi(nome,password);
		
		utenteCorrente.verifica();
		
		
		System.out.println("CREAZIONE DI UNA RICHIESTA");
		
		Richiesta richiesta = new Richiesta();
		
		richiesta.compila();      
		
		if(richiesta.controlla()) {
			System.out.println("richiesta inviata");
			richiesta.save();  // invia la richiesta
		}

		try {
			Util.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
	
	

}








