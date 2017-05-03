/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.Database;
import Model.Ventas;
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
 * @author Andres
 */
public class VentasController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        String paramId = request.getParameter("id");
        String url = "/Ventas.jsp";
        Boolean redirect = false; 
        if (operation.equals("create")){
            url = "/CreateVenta.jsp";
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
        String url = "/Ventas.jsp";
        
        Ventas venta = null;
        if(operation.equals("create")){
            try {
                venta = crearVenta(request);
            } catch (SQLException ex) {
                Logger.getLogger(VentasController.class.getName()).log(Level.SEVERE, null, ex);
                redirect = true;
                url = "CreateVenta.jsp?error=1";
                response.sendRedirect(url);
            }
        }
        else if(operation.equals("update")){
            System.out.println("Update");
            venta = actualizarVenta(request); 
        }
        else if(operation.equals("delete")){
            Ventas.delete(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("Ventas.jsp");
            redirect = true;
        }
        ServletContext context = getServletContext();
        if(!redirect){
            if(operation.equals("create")){
                venta.save();
            }
            else if(operation.equals("update")){
                venta.update();
            }
            System.out.println("Venta");
            url = "/Ventas.jsp";
            request.setAttribute("venta", venta);
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }
    
    public static List<Ventas> all(){
        List<Ventas> ventas = new ArrayList<>();
        try {
            String query = "SELECT * FROM Venta";
            ResultSet rs = Database.query(query);
            while(rs.next()) {
                Ventas venta = new Ventas(
                        rs.getInt("idVenta"),
                        rs.getInt("idProducto"),
                        rs.getInt("cantidad"),
                        rs.getString("cliente")
                );
                ventas.add(venta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ventas;
    }

    private Ventas crearVenta(HttpServletRequest request) throws SQLException {
        int id = nextId();
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String cliente = request.getParameter("cliente");
        Ventas venta = new Ventas(id, idProducto, cantidad, cliente);
        return venta; 
    }

    private Ventas actualizarVenta(HttpServletRequest request) {
        System.out.println("Actualizar");
        int id = Integer.parseInt(request.getParameter("idVenta"));
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String cliente = request.getParameter("cliente");
        Ventas venta = getVenta(id);
        if(idProducto == 0){
            idProducto = venta.getIdProducto();
        }
        if(cantidad == 0){
            cantidad = venta.getCantidad();
        }
        if(cliente.equals("")){
            cliente = venta.getCliente();
        }
        venta.setIdProducto(idProducto);
        venta.setCantidad(cantidad);
        venta.setCliente(cliente);
        return venta;
    }

    private int nextId() throws SQLException {
        String query;
        query = "SELECT max(idVenta) FROM Venta"; 
        try {
            ResultSet rs = Database.query(query);
            if (rs.first()) {
                return rs.getInt("max(idVenta)") + 1;
            }
            else{
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return 0;
    }

    private Ventas getVenta(int id) {
        Ventas venta = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * "
                + "FROM Venta "
                + "WHERE idVenta = " + id;
            rs = Database.query(query);
            if (rs.first()){
                venta = new Ventas(
                        rs.getInt("idVenta"),
                        rs.getInt("idProducto"),
                        rs.getInt("cantidad"), 
                        rs.getString("cliente")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return venta;
    }
}
