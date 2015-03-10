/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.servlets;

import com.sharethyapp.dbclasses.SignUp;
import com.sharethyapp.helper.LoginHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author reshma
 */
public class SignUpServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");

            String entryNumber = request.getParameter("EntryNumber");
            String password = request.getParameter("Password");

            String hostelerRadio = request.getParameter("Hosteler");
            boolean isHostler = false;
            if (hostelerRadio!=null && hostelerRadio.equals("choice-Yes")) {
                isHostler = true;
            }
            
            String houseNo = request.getParameter("HouseNo");
            String streetNo = request.getParameter("StreetNo");
            String city = request.getParameter("City");
            String state = request.getParameter("State");
            String pinCode = request.getParameter("PinCode");
            
            if (pinCode.trim().equals("")) {
                pinCode = null;
            }
            String emailID = request.getParameter("EmailID");

            if (new SignUp().newUser(firstName, lastName, entryNumber, password, isHostler, houseNo, streetNo, city, state, pinCode, emailID)) {
                request.getSession().setAttribute("entrynumber", entryNumber);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome.jsp");
                dispatcher.forward(request, response);
            } else {
                request.getSession().setAttribute("entrynumber", "NA");
                request.setAttribute("error", "Some of the entry details violate our DB constraints.");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signup.jsp");
                dispatcher.forward(request, response);
            }

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
