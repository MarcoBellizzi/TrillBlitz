<%@page import="logica.Evento"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="util.*" import="java.sql.*" import="logica.*"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagina personale</title>
</head>
<body>

	<%@ include file="navbar.jsp"%>

	<%
		String visitato = request.getParameter("visitato");
		String tipoVisitato = request.getParameter("tipoVisitato");
		int follower = Musicista.getNumberFollower(visitato);

		boolean presente = false;
		for (Musicista musicista : Musicista.findAll()) {
			if (musicista.getNome().equals(visitato)) {
				presente = true;
			}
		}
		if (!presente) {
	%>

	<div>.</div>
	<div>.</div>
	<div>.</div>
	<div>.</div>
	<div>.</div>
	<div>.</div>
	Utente non presente nel sistema

	<%
		} else {
	%>




	<div class="column middle">
		<h2>
			Nome :
			<%=visitato%></h2>
		<h3>
			Tipo :
			<%=tipoVisitato%></h3>
		<h3>
			Follower :
			<%=follower%>
		</h3>

		<a
			href="segui?utente1=<%=nome%>&utente2=<%=visitato%>&tipo=<%=tipo%>">segui</a>
		<h3>Eventi in cui ha partecipato :</h3>

		<%
			String select = "select * from partecipa where utente ='" + visitato + "'";
				PreparedStatement statement = Connessione.getConnection().prepareStatement(select);
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					int codice = result.getInt("evento");
					Evento evento = Evento.find(codice);
					String locale = evento.getLocale();
					Date data = evento.getData();
		%>
		<div>.</div>
		<div>Nuovo evento</div>
		<div>
			Locale :
			<%=locale%>
		</div>
		<div>
			Data :
			<%=data%>
		</div>
		<%
				for (String partecipante : evento.getPartecipanti()) {
		%>
		<div>
			Partecipante :
			<%=partecipante%>
		</div>

		<%
				}
			}
		}
		%>
	

	</div>



</body>
</html>