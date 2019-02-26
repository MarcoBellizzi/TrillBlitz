<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*" import="util.*" import="logica.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<title>Visualizza notifiche</title>
	<link rel="stylesheet" href="css/layout.css" type="text/css">
	<script type="text/javascript" src="js/activeMenu.js"></script>

</head>
<body>

	<%@ include file="navbar.jsp" %>

	<div class="container-fluid">

		<div class="row">

		 	<div class="col-sm-8">

				<h2>Ciao <%= nome %> ecco le tue notifiche</h2>


				<%	String select = "select * from notifiche where utente = '" + nome + "'";
				PreparedStatement statement = Connessione.getConnection().prepareStatement(select);
				ResultSet result = statement.executeQuery();
				while(result.next()) {
					String messaggio = result.getString("notifica");  %>

					<div>Hai una nuova notifica : <%= messaggio %></div>

					<form method=post action="elimina?utente=<%= nome %>&notifica=<%= messaggio %>&tipo=<%= tipo %>">
						<input type=submit value=Ok>
					</form>

				<% } %>

			</div>

			<div class="customDiv"> </div>
			
			<div class="col-sm">
    				seconda colonna
    		</div>

		 </div>

	</div>

</body>
</html>