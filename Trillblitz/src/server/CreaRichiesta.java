package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.Richiesta;
import util.Connessione;

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
		
		String creatore = request.getParameter("nome");
		String locale = request.getParameter("locale");
		Date data = new Date(0);
		
		try {
			
			String dataS = request.getParameter("data");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date datasql = sdf.parse(dataS);
			data = new Date(datasql.getTime());

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ArrayList<String> partecipanti = new ArrayList<String>();
		for(int i=1; i<=5; i++) {
			String partecipante = request.getParameter("partecipante"+i); 
			if(!partecipante.equals(""))
			partecipanti.add(partecipante);
		}
		
		Richiesta richiesta = new Richiesta(creatore,locale,data,partecipanti);
		if(richiesta.controlla()) {
			richiesta.save();
			out.println("la richiesta è stata controllata e inviata");
			out.println("<div> Clicca <a href=home.jsp?tipo=musicista&nome="+creatore+" >qui</a> per tornare alla home</div>");
			
		}
		else {
			for(String errato : Richiesta.errati) {
				out.println("<div> il musicista "+errato+" non è presente nel sistema <div>");
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
