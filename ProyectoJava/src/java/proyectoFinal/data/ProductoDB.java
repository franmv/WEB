/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoFinal.data;

import static java.lang.System.console;
import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import proyectoFinal.business.Categoria;
import proyectoFinal.business.Producto;



/**
 *
 * @author Andres
 */
public class ProductoDB {
    
    public static List<Producto> getProductosbyCat(int id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * "
                + "FROM Producto "
                + "WHERE idCategoria = " + id;
        
        try {
            //ps = connection.prepareStatement(query);
            //rs = ps.executeQuery();
            rs = Database.query(query);
            ArrayList<Producto> productos = new ArrayList<>();
            
            while(rs.next()) {
                
                Producto producto = new Producto();
                producto.setIdProducto((int) rs.getLong("idProducto"));
                producto.setNombreProducto(rs.getString("nombreProducto"));
                producto.setIdCategoria(CategoriaDB.selectCategoria(rs.getInt("idCategoria")));
                producto.setCantidadUnidades(rs.getInt("cantidadUnidades"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setFoto(rs.getString("foto"));
                
                productos.add(producto);
            }
            return productos;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static List<Producto> selectProductos() {
        //ConnectionPool pool = ConnectionPool.getInstance();
       // Connection connection = pool.getConnection();
        //PreparedStatement ps = null;
     
        ResultSet rs = null;

        //This method reads in all invoices that have not been
        //processed yet. To do this, it creates a ArrayList<Invoice> of
        //Invoice objects, which each contain a User object.
        //This method returns null if no unprocessed invoices are found.
        String query = "SELECT * "
                + "FROM Producto ";
                
        try {
            //ps = connection.prepareStatement(query);
           // rs = ps.executeQuery();
            rs = Database.query(query);
            System.out.println(rs.getInt("idProducto"));
            ArrayList<Producto> productos = new ArrayList<>();
            while (rs.next()) {
                //Create a User object
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombreProducto(rs.getString("nombreProducto"));
                //producto.setIdCategoria(CategoriaDB.selectCategoria(rs.getInt("idCategoria")));
                producto.setIdCategoria(new Categoria(1, "A", "B"));
                producto.setCantidadUnidades(rs.getInt("cantidadUnidades"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setFoto(rs.getString("foto"));
                

                productos.add(producto);
            }
            
            return productos;
        } catch (SQLException e) {
            System.err.println(e);
            
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            //DBUtil.closePreparedStatement(ps);
            //pool.freeConnection(connection);
        }
    }
}
