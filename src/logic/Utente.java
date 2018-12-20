package logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import prove.InvioRichiesta;
import util.*;

public class Utente implements Dao {

	String nome;
	String password;

	public Utente() {
		nome = "";
		password = "";
	}
	
	public Utente(String n, String p) {
		nome = n;
		password = p;
	}

	public String getNome() { return nome; }
	public String getPassword() { return password; }

	@Override
	public void save() {
		try {
			String insert = "insert into utente(nome, password) values (?,?)";
			PreparedStatement statement = Util.getConnection().prepareStatement(insert);
			statement.setString(1, nome);
			statement.setString(2, password);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		try {
			String delete = "delete from utente where nome = ?";
			PreparedStatement statement = Util.getConnection().prepareStatement(delete);
			statement.setString(1, nome);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void effettuaLogIn() {     // lo stesso del luogo
		
		System.out.println("LOG IN");
		System.out.println("inserire il nome");
		String nome = InvioRichiesta.input.nextLine();
		
		boolean trovato = false;
		for(Utente utente : Utente.findAll()) {
			if(utente.getNome().equals(nome)) {
				trovato = true;
				
				this.nome = utente.getNome() ;
				this.password = utente.getPassword();
				
				System.out.println("utente trovato");
				break;
			}
		}
		
		if(!trovato) {
			System.out.println("utente non trovato");
			return;
		}
		
		System.out.println("inserire la password");
		String password = InvioRichiesta.input.nextLine();
		
		if(password.equals(this.password)) {
			System.out.println("password corretta");
		}
		else {
			System.out.println("password errata");
			this.password = "";
		}
		
	}
	
	
	
	
	
	
	
	public static ArrayList<Utente> findAll() {
		
		ArrayList<Utente> lista = new ArrayList<Utente>();

		try {

			String query = "select * from utente" ;
			PreparedStatement statement = Util.getConnection().prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				String nome = result.getString("nome");
				String password = result.getString("password");
				lista.add(new Utente(nome,password));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return lista;
		
	}
	

}










