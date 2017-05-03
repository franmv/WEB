<%-- 
    Document   : Users
    Created on : May 2, 2017, 1:22:19 AM
    Author     : RicardoRS
--%>

<%@page import="Controllers.UserController"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Usuario"%>
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
                            <th>Apellido</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>numLogin</th>
                            <th>estatusCuenta</th>
                            <th>tipoUsuario</th>
                            <th>primerLogin</th>
                    </tr>
            </thead>
            <tbody>
                <%	List<Usuario> usuarios = (List<Usuario>)UserController.all();
                    int size = (usuarios != null) ? usuarios.size() : 0;
                    for (int i=0; i<size; i++) { %>
                            <tr>
                                    <td><%= usuarios.get(i).getIdUsuario() %></td>
                                    <td><%= usuarios.get(i).getNombre() %></td>
                                    <td><%= usuarios.get(i).getApellido() %></td>
                                    <td><%= usuarios.get(i).getUsername()%></td>
                                    <td><%= usuarios.get(i).getEmail()%></td>
                                    <td><%= usuarios.get(i).getNumLogin() %></td>
                                    <td><%= usuarios.get(i).getEstatusCuenta()%></td>
                                    <td><%= usuarios.get(i).getTipoUsuario()%></td>
                                    <td><%= usuarios.get(i).getPrimerLogin() %></td>
                                    <td><a class="button" id="new" href="EditUser.jsp?id=<%=usuarios.get(i).getIdUsuario()%>">Edit</a></td>
                                    <td><form action="UserController" method="post">
                                            <input type="hidden" name="operation" value="delete"/>
                                            <input type="hidden" name="id" value="<%=usuarios.get(i).getIdUsuario()%>"/>
                                            <input type="submit" name="delete" value="delete"/>
                                        </form>	 
                                    </td>
                            </tr>
            <% } %>
            </tbody>
    </table>
    <a class="button" id="new" href="CreateUser.jsp">Nuevo Usuario</a>
    <a class="button" id="new" href="Home.jsp">Home</a>
    <footer>
        <p>&copy; Company 2017</p>
    </footer>
    </body>
</html>
