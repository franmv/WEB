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
 * @author RicardoRS
 */
public class Reporte {
    private int idReporte;
    private int idProducto;
    private int idUsuario;
    private String comentario;

    public Reporte(int idReporte, int idProducto, int idUsuario, String comentario) {
        this.idReporte = idReporte;
        this.idProducto = idProducto;
        this.idUsuario = idUsuario;
        this.comentario = comentario;
    }
    
    public Reporte(){
        
    }
    
    public static List<Reporte> all(){
        List<Reporte> reportes = new ArrayList<>();
        try {
            String query = "SELECT * FROM Reporte";
            ResultSet rs = Database.query(query);
            while(rs.next()) {
                Reporte reporte = new Reporte(
                        rs.getInt("idReporte"),
                        rs.getInt("idProducto"),
                        rs.getInt("idUsuario"),
                        rs.getString("comentario")
                );
                reportes.add(reporte);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reportes;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public boolean update() {
        String query;
        query = "UPDATE Reporte "
                + "SET idProducto="+this.idProducto+", idUsuario="+this.idUsuario+","
                + " comentario="+ "\""+this.comentario+ "\""
            +" WHERE idReporte="+this.idReporte;  
        try {
            Database.update(query);
        } catch (SQLException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        return true;
    }

    public boolean save() {
        String query;
        query = "INSERT INTO Reporte (idReporte, idProducto, idUsuario, comentario)" +
        "VALUES ('%d', '%d', '%d', '%s')";  
        try {
        Database.update(query, this.idReporte, this.idProducto, this.idUsuario, this.comentario);
        } catch (SQLException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
        return true;
    }
    
    public static void delete(int id) {
        try {
            String query = "DELETE FROM Reporte WHERE idReporte =" + id;
            Database.update(query);
        } catch (SQLException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
