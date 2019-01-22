package grafica;

import javax.swing.JFrame;

public class Main {

	public static JFrame frame;
	
	public static void main(String[] args) {
		frame = new JFrame();
		frame.setSize(1000, 700);
		frame.setLocation(200, 30);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new LogIn());
		frame.setVisible(true);
	}

}
