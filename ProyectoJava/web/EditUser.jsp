<%-- 
    Document   : EditUser
    Created on : May 2, 2017, 3:46:33 AM
    Author     : RicardoRS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Proyecto DAW</h1>
        <h2>Editar Usuario</h2>
        <form action="UserController" method="post">
            <input type="hidden" name="operation" value="update"/>
            <% if (request.getParameter("id") != null) { %>
                <input type="hidden" name="idUsuario" value="<%=request.getParameter("id")%>"/>
            <% }else{ %>
                <p><label>Id: <input type="text" name="idUsuario" required></label></p>
            <% } %>
            <p><label>Nombre: <input type="text" name="nombre" required></label></p>
            <p><label>Apellido: <input type="text" name="apellido" required></label></p>
            <p><label>Email: <input type="text" name="email" required></label></p>
            <p><label>Password: <input type="password" name="password" ></label></p>
            <input type="hidden" name="numLogin" value="0"/>
            <input type="hidden" name="estatusCuenta" value="activa"/>
            <input type="hidden" name="primerLogin" value="n"/>
            <input type="submit" name="crear"/>
        </form>	
    <hr>
    <footer>
        <p>&copy; Company 2017</p>
    </footer>
    </body>
</html>
