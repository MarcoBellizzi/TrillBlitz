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
<div id="header">Home Page</div>

<% String utente = request.getParameter("utente");
	if (utente == null) utente = "World";   
	
	String select = "select * from notifiche where utente = '" + utente + "'";
	PreparedStatement statement = Connessione.getConnection().prepareStatement(select);
	ResultSet result = statement.executeQuery();

	while(result.next()) {
		String messaggio = result.getString("notifica");    %>
		
		<div>Hai una nuova notifica :</div>
		<div><%= messaggio %></div>  	
	
	<% } %>





</body>
</html>