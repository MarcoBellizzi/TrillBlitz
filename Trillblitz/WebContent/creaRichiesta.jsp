<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="logica.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<title>Crea Richiesta</title>
	<link rel="stylesheet" href="css/layout.css" type="text/css">
	<link rel="stylesheet" href="css/Request.css" type="text/css">
	<script type="text/javascript" src="js/activeMenu.js"></script>
	
</head>

	<%@ include file="navbar.jsp" %>

	<div class="container-fluid">

		<div class="row">
		
		 <div class="col-sm-8">
		 
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
					<label>Data</label> <input type="date" name=data id="dt"/>
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
			
			<div class="col-sm">

    				seconda colonna

    		</div>
			
		</div>
    	  
	</div>


</body>
</html>

