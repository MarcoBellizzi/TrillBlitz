<%@ page language="java" contentType="text/html; charset=UTF-8" import="logica.*"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HomePage</title>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/HomePage.css" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Amatic+SC"	rel="stylesheet">
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
					<form method=post action="paginaPersonale.jsp?nome=<%= nome %>&tipo=<%= tipo %>&tipoVisitato=musicista">
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

	<div>
		<div class="column middle">
			<div id=header> Benvenuto nella tua Home Page <%= nome %> </div>
			
		</div>
	</div>


</body>
</html>


