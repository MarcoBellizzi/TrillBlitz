package logica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import prove.InvioRichiesta;
import prove.RegistrazioneUtente;
import util.*;

public class Musicista extends Utente implements Dao {

	public Musicista() {
		super();
	}
	
	public Musicista(String nome, String password, String email) { 
		super(nome,password,email); 
	}

	@Override
	public void save() {
		try {
			String insert = "insert into musicista(nome, password, email) values (?,?,?)";
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
			String delete = "delete from utente where nome = ?";
			PreparedStatement statement = Connessione.getConnection().prepareStatement(delete);
			statement.setString(1, nome);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void accedi(String nome, String password) {
		boolean trovato = false;
		for(Musicista utente : Musicista.findAll()) {
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

	public void verifica() {
		if(nome.equals("") || password.equals("")) {
			System.out.println("vuoi registrarti? ");
			String s = InvioRichiesta.input.nextLine();
			if(s.equals("si")) {
				RegistrazioneUtente.main(null);
			}
			else {
				throw new RuntimeException();
			}
		}
	}




	public static ArrayList<Musicista> findAll() {

		ArrayList<Musicista> lista = new ArrayList<Musicista>();

		try {

			String query = "select * from musicista" ;
			PreparedStatement statement = Connessione.getConnection().prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while(result.next()) {
				String nome = result.getString("nome");
				String password = result.getString("password");
				String email = result.getString("email");
				lista.add(new Musicista(nome,password,email));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return lista;

	}


}










