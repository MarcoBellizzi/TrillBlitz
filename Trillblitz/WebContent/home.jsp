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

<div id="header">Home Page</div>

<div> Benvenuto nella tua Home Page <%= visitor%> </div>
<div>.  </div>
<div>.  </div>
<div>.  </div>
<div> Clicca <a href=creaRichiesta.jsp?creatore=<%= visitor%> >qui</a> per creare una richiesta</div>

</body>
</html>