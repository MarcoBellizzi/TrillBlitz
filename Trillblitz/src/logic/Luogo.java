package logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import prove.AccettazioneRichiesta;
import prove.InvioRichiesta;
import prove.RegistrazioneUtente;
import util.Util;

public class Luogo implements Dao {

	String nome;
	String password;

	public Luogo() {
		nome = "";
		password = "";
	}

	public Luogo(String s, String p) {
		nome = s;
		password = p;
	}

	public String getNome() { return nome; };
	public String getPassword() { return password; };

	@Override
	public void save() {
		try {
			String insert = "insert into luogo values (?,?)";
			PreparedStatement statement = Util.getConnection().prepareStatement(insert);
			statement.setString(1, nome);
			statement.setString(2, password);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete() {
		try {
			String delete = "delete from luogo where nome = ?";
			PreparedStatement statement = Util.getConnection().prepareStatement(delete);
			statement.setString(1, nome);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void accedi(String nome, String password) {     // lo stesso dell utente
		
		boolean trovato = false;
		for(Utente utente : Utente.findAll()) {
			if(utente.getNome().equals(nome)) {
				trovato = true;
				
				this.nome = utente.getNome() ;
				this.password = utente.getPassword();
				
				System.out.println("utente trovato");
				break;
			}
		}
		
		if(!trovato) {
			System.out.println("utente non trovato");
			return;
		}
		
		if(password.equals(password)) {
			System.out.println("password corretta");
		}
		else {
			System.out.println("password errata");
			password = "";
		}
		
	}
	
	public void verifica() {
		if(nome.equals("") || password.equals("")) {
			System.out.println("vuoi registrarti? ");
			String s = InvioRichiesta.input.nextLine();
			if(s.equals("si")) {
				RegistrazioneUtente.main(null);
			}
			else {
				throw new RuntimeException();
			}
		}
	}

	public void visualizzaRichieste() {

		boolean almenoUna = false;
		for(Richiesta richiesta : Richiesta.findAll(nome)) {
			almenoUna = true;
			System.out.println("nuova richiesta da : " + richiesta.getCreatore());
			System.out.println("partecipanti proposti ");
			for(String utente : richiesta.getListaPartecipanti()) {
				System.out.println(utente);
			}

			System.out.println("accetti?");
			String s = AccettazioneRichiesta.input.next();

			if(s.equals("si")) {
				richiesta.accetta();    // crea l'evento
				System.out.println("richiesta accettata");
			}
			else {
				System.out.println("richiesta non accettata");
			}
		}

		if(!almenoUna)
			System.out.println("nessuna nuova richiesta");

	}


	public static ArrayList<Luogo> findAll() {

		ArrayList<Luogo> lista = new ArrayList<Luogo>();

		try {

			String query = "select * from luogo" ;
			PreparedStatement statement = Util.getConnection().prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while(result.next()) {
				String nome = result.getString("nome");
				String password = result.getString("password");
				lista.add(new Luogo(nome,password));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return lista;

	}



}











