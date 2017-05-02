/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.Database;
import Model.Categoria;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Andres
 */
public class CategoriaDB {
    
    public static Categoria selectCategoria(int id) {
        ResultSet rs = null;

        //This method reads in all invoices that have not been
        //processed yet. To do this, it creates a ArrayList<Invoice> of
        //Invoice objects, which each contain a User object.
        //This method returns null if no unprocessed invoices are found.
        String query = "SELECT * "
                + "FROM Categoria " 
                + "WHERE idCategoria = 1";
        
        try {
            rs = Database.query(query);
            Categoria cat = new Categoria();
            while (rs.next()) {
                //Create a User object
                
                cat.setIdCategoria((rs.getInt("idCategoria")));
                cat.setNombreCategoria(rs.getString("nombreCategoria"));
                cat.setDescripcion(rs.getString("descripcion"));
            }
            return cat;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } 
    }

    
}

