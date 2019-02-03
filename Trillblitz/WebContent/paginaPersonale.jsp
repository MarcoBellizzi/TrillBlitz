<%@page import="logica.Evento"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="util.*" import="java.sql.*" import="logica.*"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagina personale</title>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/HomePage.css" type="text/css" />
</head>
<body>

	<%
		String visitato = request.getParameter("visitato");
		String tipoVisitato = request.getParameter("tipoVisitato");	
		String nome = request.getParameter("nome");
		String tipo = request.getParameter("tipo");
		int follower = Musicista.getNumberFollower(visitato);
	%>

	<div>
		<nav>
			<ul id="left">
				<li>
					<form method=post action="paginaPersonale.jsp?nome=<%= nome %>&tipo=<%= tipo %>&tipoVisitato=musicista">
					<label>Cerca </label> 
						<select name="visitato">
						<%
							for (Musicista musicista : Musicista.findAll()) {
								String visitato2 = musicista.getNome();
						%>
							<option>
								<%= visitato2 %>
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
	<h2>
		Nome : <%=visitato%></h2>
	<h3>
		Tipo : <%=tipoVisitato%></h3>
	<h3>
		Follower :
		<%=follower%>
	</h3>

	<a href="segui?utente1=<%=nome%>&utente2=<%=visitato%>&tipo=<%= tipo %>">segui</a>
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
		Locale : <%=locale%>
	</div>
	<div>
		Data : <%=data%>
	</div>
	<%
		for (String partecipante : evento.getPartecipanti()) {
	%>
	<div> Partecipante : <%=partecipante%> </div>

	<%
		}
	}
%>
	
	
	</div>



</body>
</html>