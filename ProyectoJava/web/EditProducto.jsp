<%-- 
    Document   : EditProducto
    Created on : May 2, 2017, 8:21:46 PM
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
        <h2>Editar Producto</h2>
        <form action="ProductoController" method="post">
            <input type="hidden" name="operation" value="update"/>
            <% if (request.getParameter("id") != null) { %>
                <input type="hidden" name="idProducto" value="<%=request.getParameter("id")%>"/>
            <% }else{ %>
                <p><label>Id: <input type="text" name="idProducto" required></label></p>
            <% } %>
            <p><label>Nombre: <input type="text" name="nombreProducto" required></label></p>
            <p><label>Categoria: <input type="number" name="idCategoria" required></label></p>
            <p><label>Cantidad: <input type="number" name="cantidadUnidades" required></label></p>
            <p><label>Precio: <input type="number" name="precio" required></label></p>
            <p><label>Foto: <input type="text" name="foto" required></label></p>
            <br>
            <input type="submit" name="crear"/>
        </form>	
    <hr>
    <footer>
        <p>&copy; Company 2017</p>
    </footer>
    </body>
</html>
