package server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.Locale;
import logica.Musicista;
import util.Connessione;

/**
 * Servlet implementation class MyServlet
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

		Connessione.initConnection();

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
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp?nome="+nome);
			rd.forward(request, response);
		}


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
				RequestDispatcher rd = request.getRequestDispatcher("homeLocale.jsp?nome="+nome);
				rd.forward(request, response);
			}
			else {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<div>Password Errata</div>");
			}
		}
		else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<div>Utente non registrato</div>");
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
