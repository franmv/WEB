package Controllers;


import Database.Database;
import Model.Usuario;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rrs94
 */
public class LogController extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String operation = request.getParameter("operation");
		String url = "login.jsp";
                System.out.println("login");
                switch (operation) {
                    case "login":
                        String username = request.getParameter("us");
                        String password = request.getParameter("pas");
                        try {
                            String query = "SELECT id FROM User WHERE username = '%s' AND password = '%s'";
                            ResultSet rs = Database.query(query, username, password);
                            int id = UserController.validar(username, password);
                            String tipo = UserController.tipo(id);
                            String primerLogin = UserController.primerLogin(id);
                            //String tipo = "administrador";
                            if (id != 0) {
                                request.getSession().setAttribute("loggedIn", id);
                                if(primerLogin.equals("n")){
                                    if(tipo.equals("administrador") ){
                                        url = "Users.jsp";
                                    }else if(tipo.equals("vendedor")){
                                        url = "Productos.jsp";
                                    }else if(tipo.equals("gerente")) {
                                        url = "Inventario.jsp";
                                    }else if(tipo.equals("ventas")) {
                                        url = "Ventas.jsp";
                                    }else{
                                        url = "404.jsp";
                                    }
                                }else{
                                    url = "EditUser.jsp?id="+id;
                                }
                            } else if(id == 0) {
                                url = "login.jsp?error=1";
                            } else if(id == -1){
                                url = "login.jsp?error=2";
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(LogController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case "logout":
                        request.getSession().invalidate();
                        url = "login.jsp";
                        break;
                }
		response.sendRedirect(url);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LogController.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LogController.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
