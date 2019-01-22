<%@ page language="java" contentType="text/html; charset=UTF-8" import="logica.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Visualizza Richieste</title>
<link rel="stylesheet" href="css/common.css" type="text/css" />
</head>
<body>
	<div id=header >Pagina per Visualizzare le richieste</div>

	<% String nome = request.getParameter("nome"); %>
	
	Ciao <%= nome %> hai le seguenti richieste :
	
	<% for(Richiesta richiesta : Richiesta.findAll(nome)) { 
		String creatore = richiesta.getCreatore();		
		String codice = "" + richiesta.getCodice();      %>
		
		<div>Hai un nuova nuova richiesta da <%= creatore %> </div>
		
		<% for(String partecipante : richiesta.getListaPartecipanti()) {  %>
				<div>Partecipante: <%= partecipante %> </div>
		<% 	}	%>	
		
		Accetti?
		
		<form method=post action=gestisciRichiesta.jsp?codice=<%= codice %> >
			<select name=scelta>
				<option>Si</option>
				<option>No</option>
			</select>
			<input type="submit" value="gestisci" />
		</form>
		
	<% 	}	%>
	
	
</body>
</html>