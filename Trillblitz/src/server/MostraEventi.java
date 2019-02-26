package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Connessione;

/**
 * Servlet implementation class MostraEventi
 */
@WebServlet("/MostraEventi")
public class MostraEventi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MostraEventi() {
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
		
		Date data = new Date(0);

		try {

			String dataS = request.getParameter("data");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date datasql = sdf.parse(dataS);
			data = new Date(datasql.getTime());

		} catch (ParseException e) {
			e.printStackTrace();
		}

		try {
			String select = "select * from evento"; 
			PreparedStatement statement = Connessione.getConnection().prepareStatement(select);
			ResultSet result = statement.executeQuery();

			while(result.next()) {

				int codice = result.getInt("codice");
				Date data2 = result.getDate("data");
				
				if(data2.getTime()>data.getTime()) {
					out.println(codice);
				}


			}

		} catch (Exception e) {
			// TODO: handle exception
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
