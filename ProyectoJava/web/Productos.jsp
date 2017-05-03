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
        <h1>Productos</h1>
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
                                    <td><a class="button" id="new" href="EditProducto.jsp?id=<%=productos.get(i).getIdProducto()%>">Edit</a></td>
                                    <td><form action="ProductoController" method="post">
                                            <input type="hidden" name="operation" value="delete"/>
                                            <input type="hidden" name="id" value="<%=productos.get(i).getIdProducto()%>"/>
                                            <input type="submit" name="delete" value="delete"/>
                                        </form>	 
                                    </td>
                            </tr>
            <% } %>
            </tbody>
    </table>
    <a class="button" id="new" href="CreateProducto.jsp">Nuevo Producto</a>
    <a class="button" id="new" href="Home.jsp">Home</a>
    <footer>
        <p>&copy; Company 2017</p>
    </footer>
    </body>
</html>
