/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.Database;
import Model.Reporte;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author RicardoRS
 */
public class ReporteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        String paramId = request.getParameter("id");
        String url = "/Reportes.jsp";
        Boolean redirect = false; 
        if (operation.equals("create")){
            url = "/CreateReporte.jsp";
        }
        if (!redirect)
        {
            response.sendRedirect(url);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation"); 
        System.out.println("post");
        boolean redirect = false;
        String url = "/Reportes.jsp";
        
        Reporte reporte = null;
        if(operation.equals("create")){
            try {
                reporte = crearReporte(request);
            } catch (SQLException ex) {
                Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
                redirect = true;
                url = "CreateReporte.jsp?error=1";
                response.sendRedirect(url);
            }
        }
        else if(operation.equals("update")){
            System.out.println("Update");
            reporte = actualizarReporte(request); 
        }
        else if(operation.equals("delete")){
            Reporte.delete(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("Reportes.jsp");
            redirect = true;
        }
        ServletContext context = getServletContext();
        if(!redirect){
            if(operation.equals("create")){
                reporte.save();
            }
            else if(operation.equals("update")){
                reporte.update();
            }
            System.out.println("Reporte");
            url = "/Reportes.jsp";
            request.setAttribute("reporte", reporte);
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
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

    private Reporte crearReporte(HttpServletRequest request) throws SQLException {
        int id = nextId();
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        String comentario = request.getParameter("comentario");
        Reporte reporte = new Reporte(id, idProducto, idUsuario, comentario);
        return reporte; 
    }

    private Reporte actualizarReporte(HttpServletRequest request) {
        System.out.println("Actualizar");
        int id = Integer.parseInt(request.getParameter("idReporte"));
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        String comentario = request.getParameter("comentario");
        Reporte reporte = getReporte(id);
        if(idProducto == 0){
            idProducto = reporte.getIdProducto();
        }
        if(idUsuario == 0){
            idUsuario = reporte.getIdUsuario();
        }
        if(comentario.equals("")){
            comentario = reporte.getComentario();
        }
        reporte.setIdProducto(idProducto);
        reporte.setIdUsuario(idUsuario);
        reporte.setComentario(comentario);
        return reporte;
    }

    private int nextId() throws SQLException {
        String query;
        query = "SELECT max(idReporte) FROM Reporte"; 
        try {
            ResultSet rs = Database.query(query);
            if (rs.first()) {
                return rs.getInt("max(idReporte)") + 1;
            }
            else{
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return 0;
    }

    private Reporte getReporte(int id) {
        Reporte reporte = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * "
                + "FROM Reporte "
                + "WHERE idReporte = " + id;
            rs = Database.query(query);
            if (rs.first()){
                reporte = new Reporte(
                        rs.getInt("idReporte"),
                        rs.getInt("idProducto"),
                        rs.getInt("idUsuario"), 
                        rs.getString("comentario")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reporte;
    }
}
