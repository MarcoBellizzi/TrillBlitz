<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="logica.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HomePage</title>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/glyphicon.css" type="text/css">
<link rel="stylesheet" href="css/common.css" type="text/css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Amatic+SC">


</head>
<body>

	<%
		String nome = request.getParameter("nome");
		String tipo = request.getParameter("tipo");
	%>

	<header>
		<div class="search">
			<form class="search-box" method=post
				action="paginaPersonale.jsp?nome=<%=nome%>&tipo=<%=tipo%>">
				<input class="search-txt" type="text" placeholder="Search"
					aria-label="Search" name=visitato>
				<button class="search-button" type="submit">
					<img width="32" height="32" src="svg/si-glyph-magnifier.svg" />
				</button>
			</form>
		</div>

		<div class="logo">
			<a href="home.jsp?nome=<%=nome%>&tipo=<%=tipo%>"><img width="128" height="64" id="logo" src="immagini/Logo.png" /></a>
		</div>

		<nav>
			<ul>
				<%
					if (tipo.equals("musicista")) {
				%>
					<li class="nav-item"><a class="nav-link"
						href="creaRichiesta.jsp?tipo=<%=tipo%>&nome=<%=nome%>"> <img
						width="32" height="32" src="svg/si-glyph-fire.svg" />
				</a></li>

				<%
					} else {
				%>
				<li class="nav-item"><a class="nav-link"
					href="visualizzaRichieste.jsp?nome=<%=nome%>&tipo=<%=tipo%>">
						<img width="32" height="32" src="svg/si-glyph-fire.svg" />
				</a></li>

				<%
					}
				%>

				<li class="nav-item"><a class="nav-link"
					href="visualizzaNotifiche.jsp?nome=<%=nome%>&tipo=<%=tipo%>">
						<img width="32" height="32" src="svg/si-glyph-light-bulb.svg" />
				</a></li>

				<li class="nav-item"><a class="nav-link"
					href="paginaPersonale.jsp?nome=<%=nome%>&tipo=<%=tipo%>&visitato=<%=nome%>">
						<img width="32" height="32" src="svg/si-glyph-person-2.svg" />
				</a></li>

				<li class="nav-item"><a class="nav-link" href="logIn.html">
						<img width="32" height="32" src="svg/si-glyph-sign-out.svg" />
				</a></li>
			</ul>
		</nav>

		<div class="menu-toggle">
			<i class="fa fa-bars" aria-hidden="true"> <img width="32"
				height="32" src="svg/si-glyph-arrow-down.svg" />
			</i>
		</div>

	</header>



	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.menu-toggle').click(function() {
				$('nav').toggleClass('active')
			})
		})
	</script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>


</body>
</html>


