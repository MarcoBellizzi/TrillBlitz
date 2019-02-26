<%@ page language="java" contentType="text/html; charset=UTF-8" import="logica.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Visualizza Richieste</title>
</head>
<body>
<%@ include file="navbar.jsp" %>


	<div class="column middle">
		<h1 >Pagina per Visualizzare le richieste</h1>
		Ciao <%= nome %> hai le seguenti richieste :
	
	<% for(Richiesta richiesta : Richiesta.findAll(nome)) { 
		String creatore = richiesta.getCreatore();		
		String codice = "" + richiesta.getCodice();
		String data = "" + richiesta.getData();     %>
		
		<div>Hai un nuova nuova richiesta da <%= creatore %> </div>
		<div>Data : <%= data %></div>
		
		<% for(String partecipante : richiesta.getListaPartecipanti()) {  %>
				<div>Partecipante: <%= partecipante %> </div>
		<% 	}	%>	
		
		Accetti?
		
		<form method=post action="gestisciRichiesta.jsp?nome=<%= nome %>&codice=<%= codice %>" >
			<select name=scelta>
				<option>Si</option>
				<option>No</option>
			</select>
			<input type="submit" value="conferma" />
		</form>
		
	<% 	}	%>
		
	</div>

	



	
</body>
</html>