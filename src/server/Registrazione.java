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
 * Servlet implementation class Registrazione
 */
@WebServlet("/Registrazione") 
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registrazione() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nome = request.getParameter("nome");
		String tipo = request.getParameter("tipo");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		boolean occupato = false;
		for(Musicista musicista : Musicista.findAll()) {
			if(musicista.getNome().equals(nome)) {
				occupato = true;
			}
		}
		for(Locale locale : Locale.findAll()) {
			if(locale.getNome().equals(nome)) {
				occupato = true;
			}
		}
		if(!occupato) {
			if(!nome.equals("") && !password.equals("") && !email.equals("")) {
				switch(tipo) {
				case("Musicista"): {
					Musicista nuovo = new Musicista(nome,password,email);
					nuovo.save();
					RequestDispatcher rd = request.getRequestDispatcher("home.jsp?tipo=musicista&nome="+nome);  
					rd.forward(request, response);
					break;
				}
				case("Locale"): {
					Locale locale = new Locale(nome,password,email);
					locale.save();
					RequestDispatcher rd = request.getRequestDispatcher("home.jsp?tipo=locale&nome="+nome);
					rd.forward(request, response);
					break;
				}
				}
			}
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		if(occupato) {
			out.println("Il nome " + nome + " è già presente");
		}
		else {
			out.println("Sono stati lasciati dei campi non completi");
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
