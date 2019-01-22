package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.Richiesta;

/**
 * Servlet implementation class CreaRichiesta
 */
@WebServlet("/CreaRichiesta")
public class CreaRichiesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaRichiesta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String creatore = request.getParameter("creatore");
		String locale = request.getParameter("locale");
		
		ArrayList<String> partecipanti = new ArrayList<String>();
		for(int i=1; i<=5; i++) {
			String partecipante = request.getParameter("partecipante"+i); 
			if(!partecipante.equals(""))
			partecipanti.add(partecipante);
		}
		
		Richiesta richiesta = new Richiesta(3,creatore,locale,new Date(0),partecipanti);
		if(richiesta.controlla()) {
			richiesta.save();
			out.println("richiesta è stata controllata e inviata");
		}
		else {
			out.println("la richiesta contiene dei dati errati");
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
