<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="logica.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>HomePage</title>

<script type="text/javascript" src="js/activeMenu.js"></script>


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/layout.css" type="text/css">
<link rel="stylesheet" href="css/glyphicon.css" type="text/css">
<link rel="stylesheet" href="css/common.css" type="text/css">

</head>


<body>


	<div class="container-fluid-r">
		<!-- Stack the columns on mobile by making one full-width and the other half-width -->
		<div class="row">

			<div class="col-sm-4">
			
				<form action="registrati">
				
				<h1>Registrazione</h1>
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

			
		</div>
	</div>
	

</body>

</html>


