package server;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Connessione;

/**
 * Servlet implementation class EliminaNotifica
 */
@WebServlet("/EliminaNotifica")
public class EliminaNotifica extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminaNotifica() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String utente = request.getParameter("utente");
		String notifica = request.getParameter("notifica");
		String tipo = request.getParameter("tipo");
		
		try {
			String delete = "delete from notifiche where utente = '" + utente + "' and notifica = '" + notifica + "';";
			PreparedStatement statement = Connessione.getConnection().prepareStatement(delete);
			statement.executeUpdate();	
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("visualizzaNotifiche.jsp?nome="+utente+"&tipo="+tipo);  //aggiungere se la notifica viene da un locale
		rd.forward(request, response);

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
