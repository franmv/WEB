<%-- 
    Document   : CreateCandidate
    Created on : 04-dic-2015, 7:57:49
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Proyecto DAW</title>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==" crossorigin="anonymous">
		<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css" integrity="sha384-aUGj/X2zp5rLCbBxumKTCw2Z50WgIr1vs/PFN4praOTvYXWlVyh2UtNUU0KAUhAX" crossorigin="anonymous">
		<link href="css/login.css" rel="stylesheet">
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->		
	</head>

<body>
  <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#"><span class="bold-white">Proyecto DAW</span></a><br>
        </div>
      </div>
    </nav>
            <br>
            <br>
            <br>
  <h2 class="centered">NUEVO CANDIDATO</h2>
  <form action="CandidateController" method="post">
                <input type="hidden" name="operation" value="create"/>
                <p><label>Id: <input type="text" name="id" required></label></p>
                <p><label>Nombre: <input type="text" name="name" required></label></p>
                <p><label>Apellido: <input type="text" name="lastname" required></label></p>
                <p><label>Direccion: <input type="text" name="address" required></label></p>
                <p><label>Telefono: <input type="text" name="phone" required></label></p>
                <p><label>Email: <input type="text" name="email" required></label></p>
                <p><label>Titulo: <input type="text" name="title" required></label></p>
                <p><label>Universidad: <input type="text" name="university" required></label></p>
                <p><label>Certificados: <input type="text" name="certificates" required></label></p>
                <p><label>Expectativas: <input type="text" name="expectatives" required></label></p>
                <p><label>Trabajo Anterior: <input type="text" name="previous" required></label></p>
                <input type="hidden" name="interview" value="3"/>
                <input type="submit" name="crear"/>
</form>	
    
    <hr>
    <footer>
        <p>&copy; Company 2015</p>
    </footer>
    </div> <!-- /container -->
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>	
	<script src="js/editarCandidato.js"></script>
	
</body>

</html>