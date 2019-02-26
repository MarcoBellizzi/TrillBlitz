<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*" import="util.*" import="logica.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Visualizza notifiche</title>
</head>
<body>

	<%@ include file="navbar.jsp" %>


	
		<h2>Ciao <%= nome %> ecco le tue notifiche</h2>
			
	
<%	String select = "select * from notifiche where utente = '" + nome + "'";
	PreparedStatement statement = Connessione.getConnection().prepareStatement(select);
	ResultSet result = statement.executeQuery();

	while(result.next()) {
		String messaggio = result.getString("notifica");  %>
		
		<div>Hai una nuova notifica :</div>
		<div><%= messaggio %></div>
	
		<form method=post action="elimina?utente=<%= nome %>&notifica=<%= messaggio %>&tipo=<%= tipo %>">
			<input type=submit value=ok>
		</form>
	
	<% } %>
	
<a  href="home.jsp?parametro=piti" ></a>

</body>
</html>