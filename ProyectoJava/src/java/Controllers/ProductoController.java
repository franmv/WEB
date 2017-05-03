/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.Database;
import Model.Candidate;
import Model.Producto;
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
public class ProductoController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        String paramId = request.getParameter("id");
        String url = "/Productos.jsp";
        Boolean redirect = false; 
        if (operation.equals("create")){
            url = "/CreateProducto.jsp";
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
        String url = "/Productos.jsp";
        
        Producto producto = null;
        if(operation.equals("create")){
            try {
                producto = crearProducto(request);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
                redirect = true;
                url = "CreateProducto.jsp?error=1";
                response.sendRedirect(url);
            }
        }
        else if(operation.equals("update")){
            System.out.println("Update");
            producto = actualizarProducto(request); 
        }
        else if(operation.equals("delete")){
            Producto.delete(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("Productos.jsp");
            redirect = true;
        }
        ServletContext context = getServletContext();
        if(!redirect){
            if(operation.equals("create")){
                producto.save();
            }
            else if(operation.equals("update")){
                producto.update();
            }
            System.out.println("Producto");
            url = "/Productos.jsp";
            request.setAttribute("producto", producto);
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }
    
    
    public static List<Producto> getProductosbyCat(int id) {
        List<Producto> productos = new ArrayList<>();
        try {
            String query = "SELECT * "
                + "FROM Producto "
                + "WHERE idCategoria = " + id;
            ResultSet rs = Database.query(query);
            while(rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("nombreProducto"),
                        rs.getInt("idCategoria"),
                        rs.getInt("cantidadUnidades"), 
                        rs.getDouble("precio"), 
                        rs.getString("foto")
                );
                productos.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }
    
    
    public static List<Producto> all(){
        List<Producto> productos = new ArrayList<>();
        try {
            String query = "SELECT * FROM producto";
            ResultSet rs = Database.query(query);
            while(rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("nombreProducto"),
                        rs.getInt("idCategoria"),
                        rs.getInt("cantidadUnidades"), 
                        rs.getDouble("precio"), 
                        rs.getString("foto")
                );
                productos.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }

    private Producto crearProducto(HttpServletRequest request) throws SQLException {
        int id = nextId();
        String nombre = request.getParameter("nombreProducto");
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        int cantidadUnidades = Integer.parseInt(request.getParameter("cantidadUnidades"));
        double precio = Double.parseDouble(request.getParameter("precio"));
        String foto = request.getParameter("foto");
        Producto producto = new Producto(id, nombre, idCategoria, cantidadUnidades, precio, foto);
        return producto; 
    }

    private Producto actualizarProducto(HttpServletRequest request) {
        System.out.println("Actualizar");
        int id = Integer.parseInt(request.getParameter("idProducto"));
        String nombre = request.getParameter("nombreProducto");
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        int cantidadUnidades = Integer.parseInt(request.getParameter("cantidadUnidades"));
        double precio = Double.parseDouble(request.getParameter("precio"));
        String foto = request.getParameter("foto");
        System.out.println("producto"+ id);
        Producto producto = getProducto(id);
        System.out.println(producto.getIdProducto());
        if(nombre.equals("")){
            nombre = producto.getNombreProducto();
        }
        if(idCategoria == 0){
            idCategoria = producto.getIdCategoria();
        }
        if(cantidadUnidades == 0){
            cantidadUnidades = producto.getCantidadUnidades();
        }
        if(precio == 0){
            precio = producto.getPrecio();
        }
        if(foto.equals("")){
            foto = producto.getFoto();
        }
        producto.setNombreProducto(nombre);
        producto.setIdCategoria(idCategoria);
        producto.setCantidadUnidades(cantidadUnidades);
        producto.setPrecio(precio);
        producto.setFoto(foto);
        return producto;
    }

    private int nextId() throws SQLException {
        String query;
        query = "SELECT max(idProducto) FROM producto"; 
        try {
            ResultSet rs = Database.query(query);
            if (rs.first()) {
                return rs.getInt("max(idProducto)") + 1;
            }
            else{
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return 0;
    }

    private Producto getProducto(int id) {
        Producto producto = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * "
                + "FROM producto "
                + "WHERE idProducto = " + id;
            rs = Database.query(query);
            if (rs.first()){
                producto = new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("nombreProducto"),
                        rs.getInt("idCategoria"),
                        rs.getInt("cantidadUnidades"), 
                        rs.getDouble("precio"), 
                        rs.getString("foto")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return producto;
    }
}