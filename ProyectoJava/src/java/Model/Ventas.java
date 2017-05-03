/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andres
 */
public class Ventas {
    private int idVenta;
    private int idProducto;
    private int cantidad;
    private String cliente;
    
    public Ventas(){
        
    }

    public Ventas(int idVenta, int idProducto, int cantidad, String cliente) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.cliente = cliente;
    }
    
    public static List<Ventas> all(){
        List<Ventas> ventas = new ArrayList<>();
        try {
            String query = "SELECT * FROM Venta";
            ResultSet rs = Database.query(query);
            while(rs.next()) {
                Ventas venta = new Ventas(
                        rs.getInt("idVenta"),
                        rs.getInt("idProducto"),
                        rs.getInt("cantidad"),
                        rs.getString("cliente")
                );
                ventas.add(venta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ventas;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    public boolean update() {
        String query;
        query = "UPDATE Venta "
                + "SET idProducto="+this.idProducto+", cantidad="+this.cantidad+","
                + " cliente="+ "\""+this.cliente+ "\""
            +" WHERE idVenta="+this.idVenta;  
        try {
            Database.update(query);
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        return true;
    }

    public boolean save() {
        String query;
        query = "INSERT INTO Venta (idVenta, idProducto, cantidad, cliente)" +
        "VALUES ('%d', '%d', '%d', '%s')";  
        try {
        Database.update(query, this.idVenta, this.idProducto, this.cantidad, this.cliente);
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        return true;
    }
    
    public static void delete(int id) {
        try {
            String query = "DELETE FROM Venta WHERE idVenta =" + id;
            Database.update(query);
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
