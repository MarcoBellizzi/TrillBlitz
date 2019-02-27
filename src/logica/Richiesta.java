package logica;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.Connessione;

public class Richiesta implements Dao {

	int codice;
	String creatore;
	String locale; 
	Date data;
	ArrayList<String> listaPartecipanti;

	public Richiesta() {};

	public Richiesta(String creatore, String locale, Date data, ArrayList<String> listaPartecipanti) {
		this.creatore = creatore;
		this.locale = locale;
		this.data = data;
		this.listaPartecipanti = listaPartecipanti;
	}
	
	public Richiesta(int codice, String creatore, String locale, Date data, ArrayList<String> listaPartecipanti) {
		this.codice = codice;
		this.creatore = creatore;
		this.locale = locale;
		this.data = data;
		this.listaPartecipanti = listaPartecipanti;
	}

	public int getCodice() { return codice; };
	public String getCreatore() { return creatore; };
	public String getLuogo() { return locale; };
	public Date getData() { return data; };
	public ArrayList<String> getListaPartecipanti() { return listaPartecipanti; };

	public static ArrayList<String> errati;	
	
	public boolean controlla() {
		errati = new ArrayList<String>();
		
		boolean trovato = false;
		for(Locale locale : Locale.findAll()) {
			if(locale.getNome().equals(this.locale)) {
				trovato = true;
			}
		}
		if(!trovato) {
			return false;
		}

		boolean completo = true;
		for(String partecipante : listaPartecipanti) {
			trovato = false;
			for(Musicista utente : Musicista.findAll()) {
				if(partecipante.equals(utente.getNome())) {
					trovato = true;
				}
			}
			if(!trovato) {
				errati.add(partecipante);
				completo = false;
			}
		}
		return completo;
	}

	
	public void accetta() {

		try {
			
			Evento evento = new Evento(codice,locale,data);
			evento.save();
			
			String query = "select * from richiede where richiesta = " + codice;
			PreparedStatement statement = Connessione.getConnection().prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while(result.next()) {

				String utente = result.getString("utente");

				String insert = "insert into partecipa(utente,evento) values(?,?)" ;
				statement = Connessione.getConnection().prepareStatement(insert);
				statement.setString(1, utente);
				statement.setInt(2, codice);
				statement.executeUpdate();
				
				insert = "insert into notifiche(utente,notifica) values(?,?)";
				statement = Connessione.getConnection().prepareStatement(insert);
				statement.setString(1, utente);
				statement.setString(2, "sei stato aggiunto da " + creatore + " ad un nouvo evento presso il locale " + locale);
				statement.executeUpdate();
				
			}

			String insert = "insert into partecipa(utente,evento) values(?,?)";
			statement = Connessione.getConnection().prepareStatement(insert);
			statement.setString(1, creatore);
			statement.setInt(2, codice);
			statement.executeUpdate();
			
			insert = "insert into notifiche(utente,notifica) values(?,?)";
			statement = Connessione.getConnection().prepareStatement(insert);
			statement.setString(1, creatore);
			statement.setString(2, "la tua richiesta presso " + locale + " è stata accettata");
			statement.executeUpdate();

			String delete = "delete from richiede where richiesta = " + codice;
			statement = Connessione.getConnection().prepareStatement(delete);
			statement.executeUpdate();

			delete = "delete from richiesta where codice = " + codice;
			statement = Connessione.getConnection().prepareStatement(delete);
			statement.executeUpdate();
			
			String select = "select * from partecipa where evento = " + codice;
			statement = Connessione.getConnection().prepareStatement(select);
			result = statement.executeQuery();
			while(result.next()) {
				String utente = result.getString("utente");
				for(String follower : Musicista.getFollower(utente)) {
					insert = "insert into notifiche(utente,notifica) values(?,?)";
					statement = Connessione.getConnection().prepareStatement(insert);
					statement.setString(1, follower);
					statement.setString(2, "il tuo amico "+utente+" partecipa ad un nuovo evento presso "+locale+" in data"+data+" .");
					statement.executeUpdate();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 

	}


	@Override
	public void save() {
		try {
			String insert = "insert into richiesta(creatore,locale,data) values (?,?,?)";
			PreparedStatement statement = Connessione.getConnection().prepareStatement(insert);
			statement.setString(1, creatore);
			statement.setString(2, locale);
			statement.setDate(3, data);
			statement.executeUpdate();


			String query = "select codice from richiesta where creatore = '"+creatore+"' and locale = '"+locale+"'";
			statement = Connessione.getConnection().prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while(result.next()) {
				codice = result.getInt("codice");
			}

			for(String partecipante : listaPartecipanti) {
				insert = "insert into richiede(utente,richiesta) values(?,?)";
				statement = Connessione.getConnection().prepareStatement(insert);
				statement.setString(1, partecipante);
				statement.setInt(2, codice);
				statement.executeUpdate();
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

	}



	@Override
	public void delete() {
		try {
			String	delete = "delete from richiesta where codice = " + codice;
			PreparedStatement statement = Connessione.getConnection().prepareStatement(delete);
			statement.executeUpdate();

			delete = "delete from richiede where richiesta = " + codice;
			statement = Connessione.getConnection().prepareStatement(delete);
			statement.executeUpdate();
			
			String insert = "insert into notifiche(utente,notifica) values(?,?)";
			statement = Connessione.getConnection().prepareStatement(insert);
			statement.setString(1, creatore);
			statement.setString(2, "la tua richiesta presso " + locale + " è stata rifiutata");
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Richiesta find(int codice) {

		Richiesta richiesta = new Richiesta();
		
		try {

			String select = "select * from richiesta where codice = " + codice; 
			PreparedStatement statement = Connessione.getConnection().prepareStatement(select);
			ResultSet result = statement.executeQuery();

			while(result.next()) {
				int codice2 = result.getInt("codice");
				String creatore = result.getString("creatore");
				String locale = result.getString("locale");
				Date data = result.getDate("data");

				ArrayList<String> listaPartecipanti = new ArrayList<String>();

				String query2 = "select * from richiede where richiesta = " + codice;
				PreparedStatement statement2 = Connessione.getConnection().prepareStatement(query2);
				ResultSet result2 = statement2.executeQuery();

				while(result2.next()) {
					String utente = result2.getString("utente");
					listaPartecipanti.add(utente);
				}

				richiesta = new Richiesta(codice2,creatore,locale,data,listaPartecipanti);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return richiesta;
	}

	public static ArrayList<Richiesta> findAll(String locale) {

		ArrayList<Richiesta> listaRichieste = new ArrayList<Richiesta>();

		try {

			String query = "select * from richiesta where locale = '" + locale + "'" ;
			PreparedStatement statement = Connessione.getConnection().prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while(result.next()) {
				int codice = result.getInt("codice");
				String creatore = result.getString("creatore");
				String locale2 = result.getString("locale");
				Date data = result.getDate("data");

				ArrayList<String> listaPartecipanti = new ArrayList<String>();

				String query2 = "select * from richiede where richiesta = " + codice;
				PreparedStatement statement2 = Connessione.getConnection().prepareStatement(query2);
				ResultSet result2 = statement2.executeQuery();

				while(result2.next()) {
					String utente = result2.getString("utente");
					listaPartecipanti.add(utente);
				}

				listaRichieste.add(new Richiesta(codice,creatore,locale2,data,listaPartecipanti));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return listaRichieste;
	}





}






