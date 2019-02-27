package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Connessione;

/**
 * Servlet implementation class Segui
 */
@WebServlet("/Segui")
public class Segui extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Segui() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {

			String utente1 = request.getParameter("utente1");
			String utente2 = request.getParameter("utente2");
			String tipo = request.getParameter("tipo");

			if(!utente1.equals(utente2)) {

				String select = "select * from segue where utente1 ='"+utente1+"' and utente2='"+utente2+"'";
				PreparedStatement statement = Connessione.getConnection().prepareStatement(select);
				ResultSet result = statement.executeQuery();
				boolean presente = false;
				while(result.next()) {
					presente = true;
				}
				if(!presente) {
					String insert = "insert into segue(utente1, utente2) values(?,?)";
					statement = Connessione.getConnection().prepareStatement(insert);
					statement.setString(1, utente1);
					statement.setString(2, utente2);
					statement.executeUpdate();
					
					insert = "insert into notifiche(utente, notifica) values(?,?)";
					statement = Connessione.getConnection().prepareStatement(insert);
					statement.setString(1, utente2);
					statement.setString(2, "hai un nuovo follower : " + utente1 + " .");
					statement.executeUpdate();

					out.println("<div> Da adesso segui " + utente2 + " </div>");
				}
				else {
					out.println("<div> Segui già " + utente2 + " </div>");
				}
			}
			else {
				out.println("<div> Non puoi seguire te stesso </div>");
			}
			out.println("Clicca <a href=home.jsp?nome="+utente1+"&tipo="+tipo+" >qui</a> per tornare alla home page");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
