/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoFinal.business;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author eugeniorangel
 */
public class Cliente implements Serializable {
    private Long idCliente;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String empresa;
    private String direccion;
    private String ciudad;
    private String pais;
    private String telefono;
    private String correo;
    private Date fechaRegistro;
    
    public Cliente() {
        nombre = "";
        apellido = "";
        empresa = "";
        direccion = "";
        ciudad = "";
        pais = "";
        telefono = "";
        correo = "";
    }
    
    public Long getIdCliente() {
        return idCliente;
    }
    
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    
    public String getEmpresa() {
        return empresa;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    public String getCiudad() {
        return ciudad;
    }
    
    public void setPais(String pais) {
        this.pais = pais;
    }
    
    public String getPais() {
        return pais;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    public Date getFechaRegistro() {
        return fechaRegistro;
    }
    
}
