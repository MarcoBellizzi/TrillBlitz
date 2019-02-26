<%@page import="logica.Evento"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="util.*" import="java.sql.*" import="logica.*"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagina personale</title>
<link rel="stylesheet" href="css/layout.css" type="text/css">
<script type="text/javascript" src="js/activeMenu.js"></script>
</head>
<body>

	<%@ include file="navbar.jsp"%>

	<%
		String visitato = request.getParameter("visitato");
		int follower = Musicista.getNumberFollower(visitato);
		String tipoVisitato = "musicista";

		boolean presente = false;
		for (Musicista musicista : Musicista.findAll()) {
			if (musicista.getNome().equals(visitato)) {
				presente = true;
			}
		}
		for (Locale locale : Locale.findAll()) {
			if (locale.getNome().equals(visitato)) {
				presente = true;
				tipoVisitato = "locale";
			}
		}
		if (!presente) {
	%>

	<div>.</div>
	<div>.</div>
	<div>.</div>
	<div>.</div>
	<div>.</div>
	<div>.</div>
	<h1>Utente non presente nel sistema</h1>

	<%
		} else {
	%>

	<div class="container-fluid">
		<!-- Stack the columns on mobile by making one full-width and the other half-width -->
		<div class="row">


			<div class="card" style="whidt: 24rem">

				<div class="card-body">

					<h5 class="card-title"><%=visitato%></h5>

					<h3 class="card-text">
						Tipo
						<%=tipoVisitato%></h3>



					<table class="table">

						<tr>
							<th scope="col">Follower</th>
							<th scope="col">Seguiti</th>
						</tr>

						<tr>
							<td><%=follower%></td>
							<td>#</td>
						</tr>

					</table>

					<a
						href="segui?utente1=<%=nome%>&utente2=<%=visitato%>&tipo=<%=tipo%>">Follow</a>




					<%
						}
					%>

				</div>

			</div>


		</div>


		<div class="customDiv">
			<h2>Contenuti</h2>
			<h3>Eventi in cui ha partecipato</h3>

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
				Locale :
				<%=locale%>
			</div>
			<div>
				Data :
				<%=data%>
			</div>
			<%
				for (String partecipante : evento.getPartecipanti()) {
			%>
			<div>
				Partecipante :
				<%=partecipante%>
			</div>

			<%
				}
				}
			%>


		</div>
	</div>




</body>
</html>