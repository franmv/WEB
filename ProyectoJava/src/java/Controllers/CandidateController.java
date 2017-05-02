/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Candidate;
import java.io.IOException;
import java.sql.SQLException;
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
 * @author rrs94
 */
public class CandidateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        String paramId = request.getParameter("id");
        String url = "/404.jsp";
        Boolean redirect = false; 
        if (operation == null || operation.equals("login")){
            List<Candidate> candidates = Candidate.all();
            request.setAttribute("candidates", candidates);
            url = "/CreateCandidate.jsp";
        }
        else if (operation.equals("edit") || operation.equals("show") || operation.equals("delete")){
            int id = Integer.parseInt(paramId);
            Candidate candidate = Candidate.getCandidate(id);
            if (candidate != null){
                request.setAttribute("candidate", candidate);
            }
            switch (operation) {
                case "show":
                    url = "/ShowCandidate.jsp";
                    break;
                case "edit":
                    url = "/EditCandidate.jsp";
                    break;
                case "delete":
                    candidate.delete();
                    response.sendRedirect("candidates");
                    redirect = true;
                    break;
            }
        }
        else if (operation.equals("create")){
            url = "/CreateCandidate.jsp";
        }
        if (!redirect)
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation"); 
        if (operation.equals("login")) {
                //Es necesario?
                doGet(request, response);
                return;
        }
        Candidate candidate = null;
        if(operation.equals("create")){
            candidate = createCandidate(request); 
        }
        else{
            candidate = updateCandidate(request); 
        }
        ServletContext context = getServletContext();
        try { 
            candidate.save();
        } catch (SQLException ex) {
            Logger.getLogger(CandidateController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        String url = "/Candidates.jsp";
        request.setAttribute("candidate", candidate); 
        RequestDispatcher dispatcher = context.getRequestDispatcher(url);
        dispatcher.forward(request, response); 
    }
    
    private Candidate createCandidate(HttpServletRequest request)
    {   int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("lastname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String title = request.getParameter("title");
        String university = request.getParameter("university");
        String certificates = request.getParameter("certificates");
        String expectatives = request.getParameter("expectatives");
        String previous = request.getParameter("previous");
        String interview = request.getParameter("interview");
        Candidate candidate = new Candidate(id,firstName, lastName, address, phone, email, title, university,
        certificates, Integer.parseInt(expectatives), previous, Integer.parseInt(interview));
        return candidate; 
    }
    
    private Candidate updateCandidate(HttpServletRequest request)
    {
        String id = request.getParameter("id"); 
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("lastname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String title = request.getParameter("title");
        String university = request.getParameter("birthday");
        String certificates = request.getParameter("certificates");
        String expectatives = request.getParameter("expectatives");
        String previous = request.getParameter("previous");
        Candidate candidate = Candidate.getCandidate(Integer.parseInt(id));
        candidate.setFirstName(firstName);
        candidate.setLastName(lastName);
        candidate.setAddress(address);
        candidate.setPhone(phone);
        candidate.setEmail(email);
        candidate.setTitle(title);
        candidate.setUniversity(university);
        candidate.setCertificates(certificates);
        candidate.setExpectatives(Integer.parseInt(expectatives));
        candidate.setPrevious(previous);
        return candidate; 
    }
}
