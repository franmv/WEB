/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoFinal.business;
import java.io.Serializable;


/**
 *
 * @author eugeniorangel
 */

public class Usuario implements Serializable {
    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String username;
    private String password;
    private Integer numLogin;
    private String estatusCuenta;
    private String tipoUsuario;
    
    public Usuario() {
        nombre = "";
        apellido = "";
        numLogin = 0;
    }
    
    public Long getId() {
        return idUsuario;
    }
 
    public void setId(Long idUsuario) {
        this.idUsuario = idUsuario;
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
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setNumLogin (Integer numLogin) {
        this.numLogin = numLogin;
    }
    
    public Integer getNumLogin() {
        return numLogin;
    }
    
    private void setEstatusCuenta(String estatusCuenta) {
        this.estatusCuenta = estatusCuenta;
    }
    
    private String getEstatusCuenta() {
        return estatusCuenta;
    }
    
    private void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    private String getTipoUsuario() {
        return tipoUsuario;
    }
    
}

