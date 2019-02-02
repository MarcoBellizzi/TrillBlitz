<%@ page language="java" contentType="text/html; charset=UTF-8" import="logica.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrazione</title>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/HomePage.css" type="text/css" />
</head>
<body>

	<div class="column middle">
			<div id="header">Registrazione</div>
	
	<form action="registrati">
		<div>
			<label for="nome">Nome</label>
			<div>
				<input name=nome />
			</div>
		</div>
		<div> 
			<label>Tipo</label>
			<div>
				<select name=tipo>
					<option>Musicista</option>
					<option>Locale</option>
				</select>
			</div> 
		</div> 
		<div>
			<label for="password">Password</label>
			<div>
				<input name="password" />
			</div>
		</div>
		<div>
			<label for="email">Email</label>
			<div>
				<input name="email" />
			</div>
		</div>
		<div>
			<input type="submit" value="registrati" />
		</div>
	</form>
				
				
				
</div>



</body>
</html>