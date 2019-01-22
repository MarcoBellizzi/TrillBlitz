package grafica;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import logica.Utente;

public class HomeMusicista extends JPanel {

	private static final long serialVersionUID = 1L;
	
	Utente utenteCorrente;
	
	public HomeMusicista(Utente utenteCorrente) {
		super();
		this.utenteCorrente = utenteCorrente;
		setLayout(null);
		setFont(new Font("", Font.BOLD, 60));
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawString("Home page di "+utenteCorrente.getNome()+"/nTipo : Musicista", 100, 100);
	}

}
