package prove;

import java.sql.SQLException;
import java.util.Scanner;

import logica.Musicista;
import logica.Richiesta;
import util.Connessione;

public class InvioRichiesta {
	
	public static Scanner input = new Scanner(System.in);
	
	public static Musicista musicistaCorrente;
	
	public static void main(String[] args) {
		
		Connessione.initConnection();
		
		System.out.println("LOG IN");
		System.out.println("inserire il nome");
		String nome = input.nextLine();
		System.out.println("inserire la password");
		String password = input.nextLine();
		
		musicistaCorrente = new Musicista();
		
		musicistaCorrente.accedi(nome,password);
		
		musicistaCorrente.verifica();
		
		
		System.out.println("CREAZIONE DI UNA RICHIESTA");
		
		Richiesta richiesta = new Richiesta();
		
		richiesta.compila();      
		
		if(richiesta.controlla()) {
			System.out.println("richiesta inviata");
			richiesta.save();  
		}

		try {
			Connessione.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
	
	

}








