<%-- 
    Document   : Ventas
    Created on : May 2, 2017, 2:09:48 AM
    Author     : RicardoRS
--%>

<%@page import="Controllers.VentasController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Ventas"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Ventas</h1>
        <form method="post" action="LogController">
            <input type="hidden" name="operation" value="logout"/>
            <input type="submit" value="logout">
        </form>
        <table class="order-table table">
            <thead>
                    <tr>
                            <th>Id</th>
                            <th>Id Producto</th>
                            <th>Cantidad</th>
                            <th>Cliente</th>
                            <th></th>
                    </tr>
            </thead>
            <tbody>
                <%	List<Ventas> ventas = (List<Ventas>)VentasController.all();
                    int size = (ventas != null) ? ventas.size() : 0;
                    for (int i=0; i<size; i++) { %>
                            <tr>
                                    <td><%= ventas.get(i).getIdVenta() %></td>
                                    <td><%= ventas.get(i).getIdProducto() %></td>
                                    <td><%= ventas.get(i).getCantidad() %></td>
                                    <td><%= ventas.get(i).getCliente()%></td>
                                    <td><a class="button" id="new" href="EditVenta.jsp?id=<%=ventas.get(i).getIdVenta()%>">Edit</a></td>
                                    <td><form action="VentasController" method="post">
                                            <input type="hidden" name="operation" value="delete"/>
                                            <input type="hidden" name="id" value="<%=ventas.get(i).getIdVenta()%>"/>
                                            <input type="submit" name="delete" value="delete"/>
                                        </form>	 
                                    </td>
                            </tr>
            <% } %>
            </tbody>
    </table>
    <a class="button" id="new" href="CreateVenta.jsp">Nueva Venta</a>
    <a class="button" id="new" href="Home.jsp">Home</a>
        <footer>
            <p>&copy; Company 2017</p>
        </footer>
    </body>
</html>
