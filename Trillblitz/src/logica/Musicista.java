package logica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	public static ArrayList<String> getFollower(String nome) {
		ArrayList<String> lista = new ArrayList<String>();
		
		try {
			String select = "select * from segue where utente2 = '" + nome + "'";
			PreparedStatement statement = Connessione.getConnection().prepareStatement(select);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				lista.add(result.getString("utente1"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	public static int getNumberFollower(String nome) {
		int numero = 0;
		
		try {
			String select = "select count(*) from segue where utente2 ='" + nome + "'";
			PreparedStatement statement = Connessione.getConnection().prepareStatement(select);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				numero = result.getInt("count");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return numero;
	}

}










