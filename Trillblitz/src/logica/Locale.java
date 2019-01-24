package logica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.Connessione;

public class Locale extends Utente implements Dao {

	public Locale() {
		super();
	}

	public Locale(String nome, String password, String email) {
		super(nome,password,email);
	}

	@Override
	public void save() {
		try {
			String insert = "insert into locale values (?,?,?)";
			PreparedStatement statement = Connessione.getConnection().prepareStatement(insert);
			statement.setString(1, nome);
			statement.setString(2, password);
			statement.setString(3, email);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete() {
		try {
			String delete = "delete from locale where nome = ?";
			PreparedStatement statement = Connessione.getConnection().prepareStatement(delete);
			statement.setString(1, nome);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void accedi(String nome, String password) {     
		boolean trovato = false;
		for(Locale utente : Locale.findAll()) {
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
			this.nome = "";
			return;
		}
		
		if(password.equals(password)) {
			System.out.println("password corretta");
		}
		else {
			System.out.println("password errata");
			password = "";
		}
		
	}
	

	public static ArrayList<Locale> findAll() {

		ArrayList<Locale> lista = new ArrayList<Locale>();

		try {

			String query = "select * from locale" ;
			PreparedStatement statement = Connessione.getConnection().prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while(result.next()) {
				String nome = result.getString("nome");
				String password = result.getString("password");
				String email = result.getString("email");
				lista.add(new Locale(nome,password,email));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return lista;

	}



}











