package logica;


import java.sql.*;

import util.Connessione;

public class Evento implements Dao {

	int codice;
	String luogo;
	Date data;
	
	public Evento(int codice, String luogo, Date data) {
		this.codice = codice;
		this.luogo = luogo;
		this.data = data;
	}
	
	@Override
	public void save() {
		try {
			String insert = "insert into evento(codice, locale, data) values (?,?,?)";
			PreparedStatement statement = Connessione.getConnection().prepareStatement(insert);
			statement.setInt(1, codice);
			statement.setString(2, luogo);
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

}
