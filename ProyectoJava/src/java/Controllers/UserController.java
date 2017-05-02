/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.Database;
import Model.Usuario;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author RicardoRS
 */
public class UserController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        String paramId = request.getParameter("id");
        String url = "/404.jsp";
        Boolean redirect = false; 
        if (operation.equals("create")){
            url = "/CreateUser.jsp";
        }
        if (!redirect)
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation"); 
        System.out.println("post");
        
        Usuario usuario = null;
        if(operation.equals("create")){
            usuario = crearUsuario(request); 
        }
        else if(operation.equals("update")){
            usuario = actualizarUsuario(request); 
        }
        ServletContext context = getServletContext();
        try { 
            if(operation.equals("create")){
                usuario.save();
            }
            else if(operation.equals("update")){
                usuario.update(); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        System.out.println("User");
        String url = "/Users.jsp";
        request.setAttribute("user", usuario); 
        RequestDispatcher dispatcher = context.getRequestDispatcher(url);
        dispatcher.forward(request, response); 
    }
    
    public static List<Usuario> all(){
        List<Usuario> usuarios = new ArrayList<>();
        try {
            String query = "SELECT * FROM User";
            ResultSet rs = Database.query(query);
            while(rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("username"), 
                        rs.getString("email"), 
                        rs.getString("password"),
                        rs.getInt("numLogin"),
                        rs.getString("estatusCuenta"),
                        rs.getString("tipoUsuario"),
                        rs.getString("primerLogin")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    public static int validar(String username, String password) {
        try {
            String query = "SELECT id FROM User WHERE username = '%s' AND password = '%s'";
            ResultSet rs = Database.query(query, username, password);
            if (rs.first()) {
                return rs.getInt("id");
            }
            else{
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static String tipo(int id) {
        try {
            String query = "SELECT tipoUsuario FROM User WHERE id = "+id;
            ResultSet rs = Database.query(query);
            if (rs.first()) {
                return rs.getString("tipoUsuario");
            }
            else{
                return "";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public static Usuario getUsuario(int id){
        Usuario usuario = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM User WHERE id =" + id;
            rs = Database.query(query);
            if (rs.first()){
                usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("username"), 
                        rs.getString("email"), 
                        rs.getString("password"),
                        rs.getInt("numLogin"),
                        rs.getString("estatusCuenta"),
                        rs.getString("tipoUsuario"),
                        rs.getString("primerLogin")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    private Usuario crearUsuario(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int numLogin = Integer.parseInt(request.getParameter("numLogin"));
        String estatusCuenta = request.getParameter("estatusCuenta");
        String tipoUsuario = request.getParameter("tipoUsuario");
        String primerLogin = request.getParameter("primerLogin");
        Usuario usuario = new Usuario(id,nombre, apellido, username, email, password, numLogin, estatusCuenta,
        tipoUsuario, primerLogin);
        return usuario; 
    }

    private Usuario actualizarUsuario(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int numLogin = Integer.parseInt(request.getParameter("numLogin"));
        String estatusCuenta = request.getParameter("estatusCuenta");
        String tipoUsuario = request.getParameter("tipoUsuario");
        String primerLogin = request.getParameter("primerLogin");
        Usuario usuario = getUsuario(id);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setUsername(username);
        usuario.setEmail(email);
        if(password.equals("")){
            password = usuario.getPassword();
        }
        usuario.setPassword(password);
        usuario.setNumLogin(numLogin);
        usuario.setEstatusCuenta(estatusCuenta);
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setPrimerLogin(primerLogin);
        return usuario; 
    }
}
