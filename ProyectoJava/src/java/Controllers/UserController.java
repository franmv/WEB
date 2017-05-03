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
        String url = "/Users.jsp";
        Boolean redirect = false; 
        if (operation.equals("create")){
            url = "/CreateUser.jsp";
        }
        if (!redirect)
        {
            response.sendRedirect(url);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation"); 
        System.out.println("post");
        boolean redirect = false;
        String url = "/Users.jsp";
        
        Usuario usuario = null;
        if(operation.equals("create")){
            try {
                if(validUsername(request.getParameter("username"))){
                    usuario = crearUsuario(request);
                } else{
                    System.out.println("Invalid User");
                    redirect = true;
                    url = "CreateUser.jsp?error=1";
                    response.sendRedirect(url);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(operation.equals("update")){
            usuario = actualizarUsuario(request); 
        }
        else if(operation.equals("delete")){
            try {
                Usuario.delete(Integer.parseInt(request.getParameter("id")));
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("Users.jsp");
            redirect = true;
        }
        ServletContext context = getServletContext();
        if(!redirect){
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
            String tipo = usuario.getTipoUsuario();
            url = "/Home.jsp";
            request.setAttribute("user", usuario);
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }
    
    public int nextId() throws SQLException{
        String query;
        query = "SELECT max(id) FROM User"; 
        try {
            ResultSet rs = Database.query(query);
            System.out.println("Next Id"+rs);
            if (rs.first()) {
                return rs.getInt("max(id)") + 1;
            }
            else{
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return 0;
    }
    
    public boolean validUsername(String username) throws SQLException{
        String query;
        query = "SELECT * FROM User WHERE username="+ "\""+username+ "\""; 
        try {
            ResultSet rs = Database.query(query);
            if (rs.first()) {
                return false;
            }
            else{
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return false;
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
                int id = rs.getInt("id");
                Usuario usuario = getUsuario(id);
                if(usuario.getEstatusCuenta().equals("activa")){
                   if(usuario.getNumLogin()>0){
                       usuario.setNumLogin(0);
                       usuario.update();
                   } 
                   return id; 
                }
                System.out.println("Bloqueada");
            }
            else{
                String query2 = "SELECT id FROM User WHERE username = '%s'";
                ResultSet rs2 = Database.query(query2, username);
                if(rs2.first()){
                    int id = rs2.getInt("id");
                    Usuario usuario = getUsuario(id);
                    usuario.setNumLogin(usuario.getNumLogin() + 1);
                    int logins = usuario.getNumLogin();
                    int intentos = 3-logins;
                    System.out.println("Quedan"+ intentos +"intentos");
                    actualizarLogin(usuario);
                }
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

    private Usuario crearUsuario(HttpServletRequest request) throws SQLException {
        int id = nextId();
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
    
    private static void actualizarLogin(Usuario usuario) throws SQLException{
        if(usuario.getNumLogin()>=3){
            usuario.setEstatusCuenta("bloqueada");
        }
        usuario.update();
        System.out.println("Actualizo usuario"+usuario.getEstatusCuenta()+usuario.getNumLogin());
    }

    private Usuario actualizarUsuario(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int numLogin = Integer.parseInt(request.getParameter("numLogin"));
        String estatusCuenta = request.getParameter("estatusCuenta");
        String primerLogin = request.getParameter("primerLogin");
        Usuario usuario = getUsuario(id);
        if(nombre.equals("")){
            nombre = usuario.getNombre();
        }
        if(apellido.equals("")){
            apellido = usuario.getApellido();
        }
        if(email.equals("")){
            email = usuario.getEmail();
        }
        if(password.equals("")){
            password = usuario.getPassword();
        }
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setNumLogin(numLogin);
        usuario.setEstatusCuenta(estatusCuenta);
        usuario.setPrimerLogin(primerLogin);
        return usuario; 
    }
    
    static String primerLogin(int id) {
        try {
            String query = "SELECT primerLogin FROM User WHERE id = "+id;
            ResultSet rs = Database.query(query);
            if (rs.first()) {
                return rs.getString("primerLogin");
            }
            else{
                return "n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "n";
    }
}
