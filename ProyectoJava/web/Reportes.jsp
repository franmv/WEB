<%-- 
    Document   : Inventario
    Created on : May 2, 2017, 2:09:42 AM
    Author     : RicardoRS
--%>

<%@page import="Controllers.ReporteController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Reporte"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Reportes de Inventario</h1>
        <form method="post" action="LogController">
            <input type="hidden" name="operation" value="logout"/>
            <input type="submit" value="logout">
        </form>
        <table class="order-table table">
            <thead>
                    <tr>
                            <th>Id</th>
                            <th>Id Producto</th>
                            <th>Id Usuario</th>
                            <th>Comentario</th>
                            <th></th>
                    </tr>
            </thead>
            <tbody>
                <%	List<Reporte> reportes = (List<Reporte>)ReporteController.all();
                    int size = (reportes != null) ? reportes.size() : 0;
                    for (int i=0; i<size; i++) { %>
                            <tr>
                                    <td><%= reportes.get(i).getIdReporte() %></td>
                                    <td><%= reportes.get(i).getIdProducto() %></td>
                                    <td><%= reportes.get(i).getIdUsuario() %></td>
                                    <td><%= reportes.get(i).getComentario()%></td>
                                    <td><a class="button" id="new" href="EditReporte.jsp?id=<%=reportes.get(i).getIdReporte()%>">Edit</a></td>
                                    <td><form action="ReporteController" method="post">
                                            <input type="hidden" name="operation" value="delete"/>
                                            <input type="hidden" name="id" value="<%=reportes.get(i).getIdReporte()%>"/>
                                            <input type="submit" name="delete" value="delete"/>
                                        </form>	 
                                    </td>
                            </tr>
            <% } %>
            </tbody>
    </table>
    <a class="button" id="new" href="CreateReporte.jsp">Nuevo Reporte</a>
    <a class="button" id="new" href="Home.jsp">Home</a>
        <footer>
            <p>&copy; Company 2017</p>
        </footer>
    </body>
</html>
