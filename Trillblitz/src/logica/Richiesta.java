package logica;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import prove.InvioRichiesta;
import util.Connessione;

public class Richiesta implements Dao {

	int codice;
	String creatore;
	String locale; 
	Date data;
	ArrayList<String> listaPartecipanti;

	public Richiesta() {};

	public Richiesta(int codice, String creatore, String locale, Date data, ArrayList<String> listaPartecipanti) {
		this.codice = codice;
		this.creatore = creatore;
		this.locale = locale;
		this.data = data;
		this.listaPartecipanti = listaPartecipanti;
	}

	public void init(String creatore, String locale, Date data, ArrayList<String> listaPartecipanti) {
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

	public void compila() {

		System.out.println("inserire il luogo in cui fare l'evento");
		String luogo = InvioRichiesta.input.nextLine();

		System.out.println("inserire la data nel formato   int anno / int(1/12) mese / int giorno");
		int anno = InvioRichiesta.input.nextInt();
		int mese = InvioRichiesta.input.nextInt()-1;
		int giorno = InvioRichiesta.input.nextInt();

		Calendar calendario = Calendar.getInstance();
		calendario.set(anno,mese,giorno);
		Date data = new Date(calendario.getTimeInMillis());

		System.out.println("inserire il numero dei partecipanti");
		int n = InvioRichiesta.input.nextInt();

		ArrayList<String> listaPartecipanti = new ArrayList<String>();
		String s = InvioRichiesta.input.nextLine();
		for(int i=0; i<n; i++) {
			System.out.println("inserire il nome del partecipante " + (i+1) );
			s = InvioRichiesta.input.nextLine();
			listaPartecipanti.add(s);
		}
		init(InvioRichiesta.musicistaCorrente.getNome(),luogo,data,listaPartecipanti);

	}


	public boolean controlla() {
		boolean trovato = false;
		for(Locale locale : Locale.findAll()) {
			if(locale.getNome().equals(this.locale)) {
				trovato = true;
			}
		}

		if(!trovato) {
			//System.out.println("non ho trovato il luogo");
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
				//System.out.println("non ho trovato l'utente " + partecipante);
				completo = false;
			}
		}
		if(completo) {
			//System.out.println("ho trovato tutti i partecipanti");
			return true;
		}
		return false;
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
				statement.setString(2, creatore + " ti ha aggiunto ad un evento. L'evento si tiene presso " + locale + 
						" in data " + data );
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






