/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoFinal.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import proyectoFinal.business.Producto;
import proyectoFinal.data.ProductoDB;

/**
 *
 * @author Andres
 */
public class VendedorController extends HttpServlet {
    
    @Override
     public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
         
         String requestURI = request.getRequestURI();
        String url = "/vendedor";
        if (requestURI.endsWith("/consultaProducto")) {
            url = consultaProducto(request, response);
        } 
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
     }
    
     @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = "/vendedor";
        if (requestURI.endsWith("/consultaProducto")) {
            url = consultaProducto(request, response);
        } 

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    
    private String consultaProducto(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        List<Producto> productos
                = ProductoDB.selectProductos();
        
        String url;
        if (productos != null) {
            if (productos.size() <= 0) {
                productos = null;
            }
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("productos", productos);
        url = "vendedor_busca.jsp";
        return url;
    }
}
