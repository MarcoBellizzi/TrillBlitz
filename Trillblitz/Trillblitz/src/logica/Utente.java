package logica;

public abstract class Utente {

	String nome;
	String password;
	String email;
	
	public Utente() {
		nome = "";
		password = "";
		email = "";
	}
	
	public Utente(String nome, String password, String email) {
		this.nome = nome;
		this.password = password;
		this.email = email;
	}
	
	public String getNome() { return nome; };
	public String getPassword() { return password; };
	public String getEmail() { return email; };
	
	public void setNome(String nome) { this.nome = nome; };
	public void setPassword(String password) { this.password = password; };
	public void setEmail(String email) { this.email = email; };
	
}
