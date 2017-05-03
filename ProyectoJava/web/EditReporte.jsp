<%-- 
    Document   : EditReporte
    Created on : May 3, 2017, 9:14:22 AM
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
        <h2>Editar Reporte</h2>
        <form action="ReporteController" method="post">
            <input type="hidden" name="operation" value="update"/>
            <% if (request.getParameter("id") != null) { %>
                <input type="hidden" name="idReporte" value="<%=request.getParameter("id")%>"/>
            <% }else{ %>
                <p><label>Id: <input type="text" name="idReporte" required></label></p>
            <% } %>
            <p><label>Producto <input type="number" name="idProducto" required></label></p>
            <input type="hidden" name="idUsuario" value="1"/>
            <p><label>Comentario: <input type="text" name="comentario" required></label></p>
            <br>
            <input type="submit" name="crear"/>
        </form>	
    <hr>
    <footer>
        <p>&copy; Company 2017</p>
    </footer>
    </body>
</html>
