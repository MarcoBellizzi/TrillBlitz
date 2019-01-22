package prove;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.Connessione;

public class ResetDatabase {

	public static void main(String[] args) {
		
		Connessione.initConnection();
		
		try {

			String delete = "delete from richiesta;";
			PreparedStatement statement;
			statement = Connessione.getConnection().prepareStatement(delete);
			statement.executeUpdate();
			
			delete = "delete from richiede;";
			statement = Connessione.getConnection().prepareStatement(delete);
			statement.executeUpdate();
			
			delete = "delete from partecipa;";
			statement = Connessione.getConnection().prepareStatement(delete);
			statement.executeUpdate();
			
			delete = "delete from evento;";
			statement = Connessione.getConnection().prepareStatement(delete);
			statement.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
}
