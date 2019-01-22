<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HomePage</title>
<link rel="stylesheet" href="css/common.css" type="text/css" />
</head>

<body>
<% String visitor = request.getParameter("nome");
	if (visitor == null) visitor = "World"; %>

<div id="header">Home Page Locale</div>

<div>Benvenuto nella tua Home Page <%= visitor%> </div>

<div>.  </div>
<div>.  </div>
<div>. </div>
Clicca <a href=visualizzaRichieste.jsp?nome=<%= visitor %>>qui</a> per visualizzare le tue richieste
</body>
</html>