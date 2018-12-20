package util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
	
	public static Connection connection;  // singleton

	public static int cont = 0;  // contatori eventi
	
	public enum Stato { IN_APPROVAZIONE , APPROVATA };
	
	public static void initConnection() {
		try {
			Driver d = new org.postgresql.Driver();
			DriverManager.registerDriver(d);
			connection = DriverManager.getConnection("jdbc:postgresql:trillblitz","rhode","rhode");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() { return connection; }

	public static int getCont() { return cont++; }

	
}
