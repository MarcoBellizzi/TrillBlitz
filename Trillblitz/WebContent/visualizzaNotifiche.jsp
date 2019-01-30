<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*" import="util.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Visualizza notifiche</title>
<link rel="stylesheet" href="css/common.css" type="text/css" />
</head>
<body>
<div id="header">Notifiche</div>

<% String utente = request.getParameter("utente");  %>

	<div> Ciao <%= utente %> ecco le tue notifiche </div>
	
<%	String select = "select * from notifiche where utente = '" + utente + "'";
	PreparedStatement statement = Connessione.getConnection().prepareStatement(select);
	ResultSet result = statement.executeQuery();

	while(result.next()) {
		String messaggio = result.getString("notifica");  %>
		
		<div>Hai una nuova notifica :</div>
		<div><%= messaggio %></div>
	
		<form method=post action="elimina?utente=<%= utente %>&notifica=<%= messaggio %>">
			<input type=submit value=ok>
		</form>
	
	<% } %>

	Clicca <a href=home.jsp?nome=<%= utente %>>qui</a> per tornare alla home page



</body>
</html>