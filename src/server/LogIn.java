package server;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.Locale;
import logica.Musicista;
import util.Connessione;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogIn() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String nome = request.getParameter("nome");
		String password = request.getParameter("password");

		boolean trovato = false;
		boolean passwordCorretta = false;
		for(Musicista musicista : Musicista.findAll()) {
			if(musicista.getNome().equals(nome)) {
				trovato = true;
				if(musicista.getPassword().equals(password)) {
					passwordCorretta = true;
				}
			}
		}

		if(trovato & passwordCorretta) {
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp?tipo=musicista&nome="+nome);
			rd.forward(request, response);
		}
		else {

			for(Locale locale : Locale.findAll()) {
				if(locale.getNome().equals(nome)) {
					trovato = true;
					if(locale.getPassword().equals(password)) {
						passwordCorretta = true;
					}
				}
			}

			if(trovato) {
				if(passwordCorretta) {
					RequestDispatcher rd = request.getRequestDispatcher("home.jsp?tipo=locale&nome="+nome);
					rd.forward(request, response);
				}
				else {
					RequestDispatcher rd = request.getRequestDispatcher("passwordSbagliata.html");
					rd.forward(request, response);
				}
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("utenteNonRegistrato.html");
				rd.forward(request, response);
			}
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		Connessione.close();
	}

}
