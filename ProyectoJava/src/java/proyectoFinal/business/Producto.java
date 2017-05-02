/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoFinal.business;

import java.text.NumberFormat;
import java.io.Serializable;
/**
 *
 * @author eugeniorangel
 */
public class Producto implements Serializable {
    
    private int idProducto;
    private String nombreProducto;
    private Categoria idCategoria;
    private int cantidadUnidades;
    private double precio;
    private String foto;
    
    public Producto() {
        
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
    
    public Categoria getIdCategoria() {
        return idCategoria;
    }
    
    public void setIdCategoria(Categoria idCategoria) {
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
    
}
