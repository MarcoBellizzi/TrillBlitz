<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="logica.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Crea Richiesta</title>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/HomePage.css" type="text/css" />
</head>
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



	<div class="column middle">
		<h1> Creatore della richiesta : <%= nome %> </h1>

		<form method="post" action=creaRichiesta?nome=<%= nome %>>
			<div>
				<label>Locale</label> 
				<select name="locale">
					<%
						String luogo;
						for (Locale locale : Locale.findAll()) {
							luogo = locale.getNome();
					%>
					<option>
						<%=luogo%>
					</option>
					<%
						}
					%>
				</select>

			</div>
			<label>Data</label> <input type="date" name=data />
			<div>
				<label>Partecipante 1</label> <input name="partecipante1" />
			</div>
			<div>
				<label>Partecipante 2</label> <input name="partecipante2" />
			</div>
			<div>
				<label>Partecipante 3</label> <input name="partecipante3" />
			</div>
			<div>
				<label>Partecipante 4</label> <input name="partecipante4" />
			</div>
			<div>
				<label>Partecipante 5</label> <input name="partecipante5" />
			</div>
			<div>
				<input type="submit" value="crea" />
			</div>
		</form>

	</div>



</body>
</html>