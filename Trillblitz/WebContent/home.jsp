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
	if (visitor == null) visitor = "Ghost"; %>

<div id="header">Home Page</div>

<div> Benvenuto nella tua Home Page <%= visitor%> </div>
<div>.  </div>
<div>.  </div>
<div>.  </div>
<div> Clicca <a href=creaRichiesta.jsp?creatore=<%= visitor%> >qui</a> per creare una richiesta</div>
<div>.  </div>
<div>.  </div>
<div>Clicca <a href=visualizzaNotifiche.jsp?utente=<%=visitor%> >qui</a> per visualizzare le notifiche </div>
<div>.  </div>
<div>.  </div>
<div>Clicca <a href="paginaPersonale.jsp?visitatore=<%= visitor %>&nome=<%=visitor%>&tipo=musicista" >qui</a> per vedere la tua pagina personale </div>
<div>.  </div>
<div>.  </div>
<div>Clicca <a href=logIn.html >qui</a> per tornare al log in </div>
<div>.  </div>
<div>.  </div>
<div>Clicca <a href=trovaMusicisti.jsp?visitatore=<%= visitor %> >qui</a> per trovare un musicista </div>

</body>
</html>













