<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="logica.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	String visitatore = request.getParameter("visitatore");
%>

	<form method=post action="paginaPersonale.jsp?visitatore=<%= visitatore %>&tipo=musicista">
		<label>Cerca Musicista</label> 
		<select name="nome">
			<%
				for (Musicista musicista : Musicista.findAll()) {
					String nome = musicista.getNome();
			%>
				<option>
					<%= nome %>
				</option>
			<%
				}
			%>
		</select>
		<input type="submit" value="cerca" />
	</form>

</body>
</html>