package logic;


import java.sql.*;

import util.Util;

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
			String insert = "insert into evento(codice, luogo, data) values (?,?,?)";
			PreparedStatement statement = Util.getConnection().prepareStatement(insert);
			statement.setInt(1, codice);
			statement.setString(2, luogo);
			statement.setDate(3,data);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		try {
			String delete = "delete from evento where codice = ?";
			PreparedStatement statement = Util.getConnection().prepareStatement(delete);
			statement.setInt(1,codice);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
