/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoFinal.business;

import java.util.*;
import java.io.Serializable;
/**
 *
 * @author eugeniorangel
 */
public class Recibo implements Serializable {
    private Long idRecibo;
    private Orden idOrden;
    private Date fecha;
    private double precio;
    private String tipo;
    
    public Recibo() {
        
    }
    
    public void setIdRecibo(Long idRecibo) {
        this.idRecibo = idRecibo;
    }
    
    public Long getIdRecibo() {
        return idRecibo;
    }
    
    public Orden getIdOrden() {
        return idOrden;
    }
    
    public void setIdOrden(Orden idOrden) {
        this.idOrden = idOrden;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
