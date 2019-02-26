package logica;


import java.sql.*;
import java.util.ArrayList;

import util.Connessione;

public class Evento implements Dao {

	int codice;
	String locale;
	Date data;

	public Evento() {};
	
	public Evento(int codice, String luogo, Date data) {
		this.codice = codice;
		this.locale = luogo;
		this.data = data;
	}

	public int getCodice() { return codice; }
	public String getLocale() { return locale; }
	public Date getData() { return data; }

	@Override
	public void save() {
		try {
			String insert = "insert into evento(codice, locale, data) values (?,?,?)";
			PreparedStatement statement = Connessione.getConnection().prepareStatement(insert);
			statement.setInt(1, codice);
			statement.setString(2, locale);
			statement.setDate(3,data);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete() {
		try {
			String delete = "delete from evento where codice = ?";
			PreparedStatement statement = Connessione.getConnection().prepareStatement(delete);
			statement.setInt(1,codice);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Evento> findAll() {

		ArrayList<Evento> lista = new ArrayList<Evento>();
		try {

			String select = "select * from evento"; 
			PreparedStatement statement;
			statement = Connessione.getConnection().prepareStatement(select);
			ResultSet result = statement.executeQuery();

			while(result.next()) {
				int codice = result.getInt("codice");
				String locale = result.getString("locale");
				Date data = result.getDate("data");
				lista.add(new Evento(codice,locale,data));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	public static Evento find(int codice) {

		Evento evento = new Evento();
		
		try {
			String select = "select * from evento where codice =" + codice; 
			PreparedStatement statement = Connessione.getConnection().prepareStatement(select);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				int codice2 = result.getInt("codice");
				String locale = result.getString("locale");
				Date data = result.getDate("data");
				evento = new Evento(codice2,locale,data);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return evento;
	}

	public ArrayList<String> getPartecipanti() {
		ArrayList<String> lista = new ArrayList<String>();

		try {

			String select = "select * from partecipa where evento =" + codice; 
			PreparedStatement statement = Connessione.getConnection().prepareStatement(select);
			ResultSet result = statement.executeQuery();

			while(result.next()) {
				lista.add(result.getString("utente"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}





















