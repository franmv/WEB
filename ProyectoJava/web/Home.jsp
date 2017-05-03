<%-- 
    Document   : Home
    Created on : May 2, 2017, 2:40:21 PM
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
        <h1>HOME</h1>
        <form method="post" action="LogController">
            <input type="hidden" name="operation" value="logout"/>
            <input type="submit" value="logout">
        </form>
        <a class="button" id="new" href="Users.jsp">Usuarios</a>
        <a class="button" id="new" href="Productos.jsp">Productos</a>
        <a class="button" id="new" href="Ventas.jsp">Ventas</a>
        <a class="button" id="new" href="Reportes.jsp">Reportes</a>
    </body>
</html>
