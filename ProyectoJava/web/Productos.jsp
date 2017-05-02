<%-- 
    Document   : vendedor_busca
    Created on : May 1, 2017, 7:22:03 PM
    Author     : Andres
--%>

<%@page import="Controllers.ProductoController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Producto"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Proyecto DAW</h1>
        <table class="order-table table">
            <thead>
                    <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Categoria</th>
                            <th>Unidades</th>
                            <th>Precio</th>
                            <th>Foto</th>
                            <th></th>
                    </tr>
            </thead>
            <tbody>
                <%	List<Producto> productos = (List<Producto>)ProductoController.all();
                    int size = (productos != null) ? productos.size() : 0;
                    for (int i=0; i<size; i++) { %>
                            <tr>
                                    <td><%= productos.get(i).getIdProducto() %></td>
                                    <td><%= productos.get(i).getNombreProducto() %></td>
                                    <td><%= productos.get(i).getIdCategoria() %></td>
                                    <td><%= productos.get(i).getCantidadUnidades()%></td>
                                    <td><%= productos.get(i).getPrecio()%></td>
                                    <td><%= productos.get(i).getFoto() %></td>
                            </tr>
            <% } %>
            </tbody>
    </table>
    <footer>
        <p>&copy; Company 2017</p>
    </footer>
    </body>
</html>
