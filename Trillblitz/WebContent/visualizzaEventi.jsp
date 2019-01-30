<%@ page language="java" contentType="text/html; charset=UTF-8" import="logica.*" import="util.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>visualizzaEventi</title>
<link rel="stylesheet" href="css/common.css" type="text/css" />
</head>
<body>
<div id="header">Pagina per visualizzare gli eventi</div>

<% 
Connessione.initConnection();


for(Evento evento : Evento.findAll()) { 
		int codice = evento.getCodice();
		String locale = evento.getLocale();
		String data = "" + evento.getData();   %>
	
	<div>.</div>
	<div> Nuovo evento </div>
	<div> Locale : <%= locale %> </div>
	<div> data : <%= data %> </div>
	
	<% for(String partecipante : evento.getPartecipanti())  { %>
	
			<div>Partecipante : <%= partecipante %></div>
	
	<% } %>
	
<% } %>

</body>
</html>