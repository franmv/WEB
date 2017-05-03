/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author eugeniorangel
 */
public class Producto {
    
    private int idProducto;
    private String nombreProducto;
    private int idCategoria;
    private int cantidadUnidades;
    private double precio;
    private String foto;

    public Producto(int idProducto, String nombreProducto, int idCategoria, int cantidadUnidades, double precio, String foto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.idCategoria = idCategoria;
        this.cantidadUnidades = cantidadUnidades;
        this.precio = precio;
        this.foto = foto;
    }
    
    public Producto() {
        
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
    
    public int getIdProducto() {
        return idProducto;
    }
    
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
    public String getNombreProducto() {
        return nombreProducto;
    }
    
    public int getIdCategoria() {
        return idCategoria;
    }
    
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    public int getCantidadUnidades() {
        return cantidadUnidades;
    }
    
    public void setCantidadUnidades(int cantidadUnidades) {
        this.cantidadUnidades = cantidadUnidades;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public String getPriceCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(precio);
    }
    
    public String getFoto() {
        return foto;
    }
    
    public void setFoto(String foto) {
        this.foto =foto;
    }

    public boolean update() {
        String query;
        query = "UPDATE producto "
                + "SET nombreProducto="+ "\""+this.nombreProducto+ "\""+", idCategoria="+this.idCategoria+","
                + " cantidadUnidades="+this.cantidadUnidades+", precio="+this.precio+", foto="+ "\""+this.foto+ "\""
            +" WHERE idProducto="+this.idProducto;  
        try {
            Database.update(query);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        return true;
    }

    public boolean save() {
        String query;
        query = "INSERT INTO producto (idProducto, nombreProducto, idCategoria, cantidadUnidades, precio,"
            + " foto)" +
        "VALUES ('%d', '%s', '%d', '%d', '%f', '%s')";  
        try {
        Database.update(query, this.idProducto, this.nombreProducto, this.idCategoria, this.cantidadUnidades, 
            this.precio, this.foto);
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        return true;
    }
    
    public static void delete(int id) {
        try {
            String query = "DELETE FROM producto WHERE idProducto =" + id;
            Database.update(query);
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
