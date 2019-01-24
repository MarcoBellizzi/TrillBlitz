<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="logica.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Crea Richiesta</title>
<link rel="stylesheet" href="css/common.css" type="text/css" />
</head>
<body>

	<div id=header>Pagina per creare una richiesta</div>

	<%
		String creatore = request.getParameter("creatore");
	%>

	<div>
		Il creatore di questa richiesta è <%=creatore%>
	</div>

	<form method="post" action=creaRichiesta?creatore=<%=creatore%>>
		<div>
			<label>Locale</label> <select name="locale">
				<%
					String nome;
					for (Locale locale : Locale.findAll()) {
						nome = locale.getNome();
				%>
				<option>
					<%=nome%>
				</option>
				<%
					}
				%>
			</select>

		</div>
		<label>Data</label> <input type=date name=data />
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
			<input type="submit" value="cliccami" />
		</div>
	</form>

</body>
</html>