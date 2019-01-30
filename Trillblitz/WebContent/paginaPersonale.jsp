<%@page import="logica.Evento"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="util.*" import="java.sql.*" import="logica.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagina personale</title>
</head>
<body>

<%
	String visitatore = request.getParameter("visitatore");
	String nome = request.getParameter("nome");
	String tipo = request.getParameter("tipo");
	int follower = Musicista.getNumberFollower(nome);
%>

	<h2>Nome : <%= nome %></h2>
	<h3>Tipo : <%= tipo %></h3>
	<h3>Follower : <%= follower %> </h3>
	<a href="segui?utente1=<%= visitatore %>&utente2=<%= nome %>">segui</a>
	<h3>Eventi in cui ha partecipato : </h3>
	
<%
	String select = "select * from partecipa where utente ='" + nome + "'";
	PreparedStatement statement = Connessione.getConnection().prepareStatement(select);
	ResultSet result = statement.executeQuery();
	while(result.next()) {
		int codice = result.getInt("evento");
		Evento evento = Evento.find(codice);
		String locale = evento.getLocale();
		Date data = evento.getData();
%>	
	<div>.</div>
	<div> Nuovo evento </div>
	<div> Locale : <%= locale %> </div>
	<div> Data : <%= data %> </div>
	
	<% for(String partecipante : evento.getPartecipanti())  { %>
			<div>Partecipante : <%= partecipante %></div>
	
	<% } %>
<%		
	}
%>

</body>
</html>