/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoFinal.business;

import java.io.Serializable;
import java.util.*;
import java.text.*;
/**
 *
 * @author eugeniorangel
 */
public class Detalles_orden implements Serializable {
    
    private Orden idOrden;
    private List<Producto> idProductos;
    private int cantidad;
    private double precio;
    
    public Detalles_orden() {
        
    }
    
    public void setIdOrden(Orden idOrden) {
        this.idOrden = idOrden;
    }
    
    public Orden getIdOrden() {
        return idOrden;
    }
    
    public void setIdProductos(List<Producto> idProductos) {
        this.idProductos = idProductos;
    }
    
    public List<Producto> getIdProductos() {
        return idProductos;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public double getPrecio() {
        return precio;
    }
    
}
