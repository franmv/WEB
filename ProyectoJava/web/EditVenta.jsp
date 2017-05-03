<%-- 
    Document   : EditVenta
    Created on : May 3, 2017, 8:46:58 AM
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
        <h2>Editar Venta</h2>
        <form action="VentasController" method="post">
            <input type="hidden" name="operation" value="update"/>
            <% if (request.getParameter("id") != null) { %>
                <input type="hidden" name="idVenta" value="<%=request.getParameter("id")%>"/>
            <% }else{ %>
                <p><label>Id: <input type="text" name="idVenta" required></label></p>
            <% } %>
            <p><label>Producto <input type="number" name="idProducto" required></label></p>
            <p><label>Cantidad: <input type="number" name="cantidad" required></label></p>
            <p><label>Cliente: <input type="text" name="cliente" required></label></p>
            <br>
            <input type="submit" name="crear"/>
        </form>	
    <hr>
    <footer>
        <p>&copy; Company 2017</p>
    </footer>
    </body>
</html>
