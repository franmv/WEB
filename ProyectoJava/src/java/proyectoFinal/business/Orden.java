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
public class Orden implements Serializable{
    private Long idOrden;
    private Cliente idCliente;
    private Usuario idVendedor;
    private Date fechaOrden;
    
    public Orden(){
        
    }
    
    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }
    
    public Long getIdOrden() {
        return idOrden;
    }
    
    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }
    
    public Cliente getIdCliente() {
        return idCliente;
    }
    
    public void setIdVendedor(Usuario idVendedor) {
        this.idVendedor = idVendedor;
    }
    
    public Date getFechaOrden() {
        return fechaOrden;
    }
    
    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }
}
