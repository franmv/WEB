
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.Database;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author rrs94
 */

public class Usuario {

    private int idUsuario;
    private String nombre;
    private String apellido;
    private String username;
    private String email;
    private String password;
    private int numLogin;
    private String estatusCuenta;
    private String tipoUsuario;
    private String primerLogin;

    public Usuario(int idUsuario, String nombre, String apellido, String username, String email, String password, Integer numLogin, String estatusCuenta, String tipoUsuario, String primerLogin) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.email = email;
        this.password = password;
        this.numLogin = numLogin;
        this.estatusCuenta = estatusCuenta;
        this.tipoUsuario = tipoUsuario;
        this.primerLogin = primerLogin;
    }
    
    public boolean save() throws SQLException{
        String query;
        query = "INSERT INTO User (id, nombre, apellido, username, email,"
            + " password, numLogin, estatusCuenta, tipoUsuario, primerLogin)" +
        "VALUES ('%d', '%s', '%s', '%s', '%s', '%s', '%d', '%s', '%s', '%s')";  
        try {
        Database.update(query, this.idUsuario, this.nombre, this.apellido, this.username, 
            this.email, this.password, this.numLogin, this.estatusCuenta, this.tipoUsuario, this.primerLogin);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        return true;
    }
    
    public boolean update() throws SQLException{
        String query;
        query = "UPDATE User "
                + "SET nombre="+ "\""+this.nombre+ "\""+", apellido="+ "\""+this.apellido+ "\""+","
                + " username="+ "\""+this.username+ "\""+", email="+ "\""+this.email+ "\""+","
            + " password="+ "\""+this.password+ "\""+", numLogin="+this.numLogin+", estatusCuenta="+ "\""+this.estatusCuenta+ "\""+","
            + " tipoUsuario="+ "\""+this.tipoUsuario+ "\""+", primerLogin="+ "\""+this.primerLogin+ "\""
            +" WHERE id="+this.idUsuario;  
        try {
        Database.update(query);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        return true;
    }
    

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrimerLogin(String primerLogin) {
        this.primerLogin = primerLogin;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public String getPrimerLogin() {
        return primerLogin;
    }
    
    public Usuario() {
        nombre = "";
        apellido = "";
        numLogin = 0;
    }
    
    public int getId() {
        return idUsuario;
    }
 
    public void setId(int idUsuario) {
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
    
    public void setNumLogin (int numLogin) {
        this.numLogin = numLogin;
    }
    
    public int getNumLogin() {
        return numLogin;
    }
    
    public void setEstatusCuenta(String estatusCuenta) {
        this.estatusCuenta = estatusCuenta;
    }
    
    public String getEstatusCuenta() {
        return estatusCuenta;
    }
    
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    
    public static void delete(int id)  throws SQLException {
        try {
            String query = "DELETE FROM User WHERE id =" + id;
            Database.update(query);
        } catch (SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
