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
public class ProductoController {
    
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
}