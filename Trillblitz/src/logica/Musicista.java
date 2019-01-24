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


}










