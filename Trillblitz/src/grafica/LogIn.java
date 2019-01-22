package grafica;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logica.Locale;
import logica.Musicista;
import util.Connessione;

public class LogIn extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JLabel nome;
	JLabel password;
	JLabel risposta;
	JTextField campoNome;
	JTextField campoPassword;
	JButton log;
	
	public LogIn() {
		super();
		setLayout(null);
		setFont(new Font("", Font.BOLD, 80));
		
		Connessione.initConnection();
		
		nome = new JLabel("Nome");
		nome.setBounds(100, 200, 300, 100);
		nome.setFont(new Font("", Font.BOLD, 50));
		add(nome);
		
		password = new JLabel("Password");
		password.setBounds(100, 350, 300, 100);
		password.setFont(new Font("", Font.BOLD, 50));
		add(password);

		risposta = new JLabel("");
		risposta.setBounds(400, 500, 500, 100);
		risposta.setFont(new Font("", Font.BOLD, 40));
		add(risposta);
		
		campoNome = new JTextField();
		campoNome.setBounds(400, 200, 500, 100);
		campoNome.setFont(new Font("", Font.BOLD, 50));
		add(campoNome);
		
		campoPassword = new JTextField();
		campoPassword.setBounds(400, 350, 500, 100);
		campoPassword.setFont(new Font("", Font.BOLD, 50));
		add(campoPassword);

		log = new JButton("Log In");
		log.setBounds(100, 500, 200, 100);
		add(log);
		
		log.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean trovato = false;
				for(Musicista utente : Musicista.findAll()) {
					if(utente.getNome().equals(campoNome.getText())) {
						trovato = true; 
						if(utente.getPassword().equals(campoPassword.getText())) {
							risposta.setText("Musicista trovato");
							Main.frame = new JFrame();
							Main.frame.setSize(1000, 700);
							Main.frame.setLocation(200, 30);
							Main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							Main.frame.setContentPane(new HomeMusicista(new Musicista(utente.getNome(),utente.getPassword(),utente.getEmail())));
							Main.frame.setVisible(true);
						}
						else {
							risposta.setText("password errata");
						}
					}
				}
				for(Locale utente : Locale.findAll()) {
					if(utente.getNome().equals(campoNome.getText())) {
						trovato = true;
						if(utente.getPassword().equals(campoPassword.getText())) {
							risposta.setText("locale trovato");
						}
						else {
							risposta.setText("password errata");
						}
					}
				}
				if(!trovato) {
					risposta.setText("utente non trovato");
				}
		
			}
		});
		
		
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawString("TRILLBLITZ", 250, 100);
	}
	
	
	
}
