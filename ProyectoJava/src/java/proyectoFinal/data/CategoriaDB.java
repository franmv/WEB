/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoFinal.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import proyectoFinal.business.Categoria;
import proyectoFinal.business.Producto;

/**
 *
 * @author Andres
 */
public class CategoriaDB {
    
    public static Categoria selectCategoria(int id) {
        //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
        //PreparedStatement ps = null;
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
            //ps = connection.prepareStatement(query);
            //rs = ps.executeQuery();
            Categoria cat = new Categoria();
            while (rs.next()) {
                //Create a User object
                
                cat.setIdCategoria((rs.getInt("idCategoria")));
                cat.setNombreCategoria(rs.getString(rs.getString("nombreCategoria")));
                cat.setDescripcion(rs.getString("descripcion"));
            }
            return cat;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            //DBUtil.closePreparedStatement(ps);
          //  pool.freeConnection(connection);
        }
    }

    
}

