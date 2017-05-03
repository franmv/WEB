<%-- 
    Document   : CreateProducto
    Created on : May 2, 2017, 7:02:54 PM
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
        <h2>Nuevo Producto</h2>
        <form action="ProductoController" method="post">
            <input type="hidden" name="operation" value="create"/>
            <p><label>Nombre: <input type="text" name="nombreProducto" required></label></p>
            <p><label>Categoria: <input type="number" name="idCategoria" required></label></p>
            <p><label>Cantidad: <input type="number" name="cantidadUnidades" required></label></p>
            <p><label>Precio: <input type="number" name="precio" required></label></p>
            <p><label>Foto: <input type="text" name="foto" required></label></p>
            <br>
            <% if (request.getParameter("error") != null) { %>
                <p class="error">Datos incorrectos.</p>
            <% } %>
            <input type="submit" name="crear"/>
        </form>	
    <hr>
    <footer>
        <p>&copy; Company 2017</p>
    </footer>
    </body>
</html>
