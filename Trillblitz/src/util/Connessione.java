package util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connessione {
	
	private static Connection connection;  // singleton
	
	public static Connection getConnection() { 
		if(connection == null) {
			try {
				Driver d = new org.postgresql.Driver();
				DriverManager.registerDriver(d);
				connection = DriverManager.getConnection("jdbc:postgresql://packy.db.elephantsql.com:5432/stzoncea","stzoncea","Bg0au3WOUQHbgcf1oz6-Z7_igoCuEHij");			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection; 
	}

	public static void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
