package util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Prova {

	public static Connection connection;
	
	public static void main(String[] args) {
		
		try {
			Driver d = new org.postgresql.Driver();
			DriverManager.registerDriver(d);
			connection = DriverManager.getConnection("jdbc:postgresql://packy.db.elephantsql.com:5432/stzoncea","stzoncea","Bg0au3WOUQHbgcf1oz6-Z7_igoCuEHij");
			
			String query = "select * from persona" ;
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while(result.next()) {
				System.out.println(result.getString("nome"));;
			}
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
