package util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connessione {
	
	public static Connection connection;  // singleton
	
	public static void initConnection() {
		try {
			Driver d = new org.postgresql.Driver();
			DriverManager.registerDriver(d);
		//	connection = DriverManager.getConnection("jdbc:postgresql://192.168.43.240:5432/trillblitz","rhode","rhode");
		//	connection = DriverManager.getConnection("jdbc:postgresql://localhost/trillblitz","rhode","rhode");
			connection = DriverManager.getConnection("jdbc:postgresql://packy.db.elephantsql.com:5432/stzoncea","stzoncea","Bg0au3WOUQHbgcf1oz6-Z7_igoCuEHij");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() { return connection; }

}
