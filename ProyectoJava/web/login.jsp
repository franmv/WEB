<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Proyecto DAW</title>
		<link href="css/login.css" rel="stylesheet">	
	</head>

<body>
    <h1>Proyecto DAW</h1>
    <form method="post" action="LogController">
        <input type="hidden" name="operation" value="login"/>
        <p><label>Usuario: <input type="text" name="us" required></label></p>

        <p><label>Contraseña: <input type="password" name="pas" required></label></p>
        <% if (request.getParameter("error") != null) { %>
            <p class="error">Datos incorrectos.</p>
        <% } else if(request.getParameter("error") == "2") {%>
            <p class="error">Tu cuenta esta bloqueada, contacta a un administrador.</p>
        <% } %>
        <input type="submit" value="Login">
    </form>
    <hr>
    <footer>
        <p>&copy; Company 2017</p>
    </footer>
</body>

</html>
