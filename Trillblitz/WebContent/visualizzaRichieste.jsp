<%@ page language="java" contentType="text/html; charset=UTF-8" import="logica.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Visualizza Richieste</title>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/HomePage.css" type="text/css" />
</head>
<body>

	<%
		String nome = request.getParameter("nome");
		String tipo = request.getParameter("tipo");
	%>


	<div>
		<nav>
			<ul id="left">
				<li>
					<form method=post action="paginaPersonale.jsp?nome=<%= nome %>&tipo=<%= tipo%>&tipoVisitato=musicista">
					<label>Cerca </label> 
						<select name="visitato">
						<%
							for (Musicista musicista : Musicista.findAll()) {
								String visitato = musicista.getNome();
						%>
							<option>
								<%= visitato %>
							</option>
						<%
							}
						%>
						</select>
					<input type="submit" value="cerca" />
					</form>
				</li>
			</ul>
			<ul id="center">
				<li><a href="home.jsp?nome=<%= nome %>&tipo=<%= tipo %>"><img src="immagini/home.png" id="logo" /></a></li>
			</ul>
			<ul id="right">
			
			<%
				if(tipo.equals("musicista")) {  
			%>
					<li><a href="creaRichiesta.jsp?tipo=<%= tipo %>&nome=<%=nome%>"> <img
							src="immagini/events.png">
					</a></li>
					
			<% 	
				}
				else {
			%>
					<li><a href="visualizzaRichieste.jsp?nome=<%=nome%>&tipo=<%= tipo %>"> <img
							src="immagini/events.png">
					</a></li>
			<%	
				}
			%>
			
				<li><a href="visualizzaNotifiche.jsp?nome=<%=nome%>&tipo=<%= tipo %>"><img
						src="immagini/notifics.png"></a></li>
				<li><a
					href="paginaPersonale.jsp?visitato=<%=nome%>&nome=<%=nome%>&tipo=<%= tipo %>&tipoVisitato=<%= tipo %>"><img
						src="immagini/profile.png"></a></li>
				<li><a href=logIn.html><img src="immagini/settings.png"></a></li>
			</ul>
		</nav>
	</div>


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