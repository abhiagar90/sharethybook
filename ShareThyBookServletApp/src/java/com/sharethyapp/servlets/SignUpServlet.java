/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.servlets;

import com.sharethyapp.dbclasses.SignUp;
import com.sharethyapp.dbclasses.UserTable;
import com.sharethyapp.dbclasses.UserTableDB;
import com.sharethyapp.helper.UtilitiesHelper;

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
        
        String defaultImagePath = request.getServletContext().getResource("/Anonymous.png").getPath();
        
        UserTable newUser = new UserTable();
        newUser.setFirstname(UtilitiesHelper.returnNullOrString(request, "firstName"));
        newUser.setLastname(UtilitiesHelper.returnNullOrString(request, "lastName"));
        
        String entryNumber = UtilitiesHelper.returnNullOrString(request, "EntryNumber");
        if (entryNumber != null) {
            entryNumber = entryNumber.toUpperCase();
        }
        newUser.setEntrynumber(entryNumber);

        //keeping entrynumbers uppercase in DB
        newUser.setPassword(UtilitiesHelper.returnNullOrString(request, "Password"));
        String rePassword = UtilitiesHelper.returnNullOrString(request, "rePassword");

        String hostelerRadio = UtilitiesHelper.returnNullOrString(request, "Hosteler");
        boolean isHostler = false;
        if (hostelerRadio != null && hostelerRadio.equals("choice-Yes")) {
            isHostler = true;
        }
        newUser.setIsHosteler(isHostler);

        newUser.setHouseNo(UtilitiesHelper.returnNullOrString(request, "HouseNo"));
        newUser.setStreetNo(UtilitiesHelper.returnNullOrString(request, "StreetNo"));
        newUser.setCity(UtilitiesHelper.returnNullOrString(request, "City"));
        newUser.setState(UtilitiesHelper.returnNullOrString(request, "State"));
        newUser.setPincode(UtilitiesHelper.returnNullOrString(request, "PinCode"));
        newUser.setEmailId(UtilitiesHelper.returnNullOrString(request, "EmailID"));

        String password= newUser.getPassword();
        if (!(password != null && rePassword != null && !password.isEmpty() && !rePassword.isEmpty() && password.equals(rePassword))) {
            request.getSession().setAttribute("entrynumber", "NA");
            request.setAttribute("error", "Passwords do not match or empty!");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signup.jsp");
            dispatcher.forward(request, response);
        } else {
            String sqlOutput = new UserTableDB().addNewUser(newUser, defaultImagePath);
            if (sqlOutput.equals("true")) {
                request.getSession().setAttribute("entrynumber", entryNumber);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome.jsp");
                dispatcher.forward(request, response);
            } else {
                request.getSession().setAttribute("entrynumber", "NA");
                request.setAttribute("error", "Some of the entries violate our DB constraints. Please check instructions above. <br> " + sqlOutput);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signup.jsp");
                dispatcher.forward(request, response);
            }
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
            Logger.getLogger(SignUpServlet.class  

.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SignUpServlet.class  

.getName()).log(Level.SEVERE, null, ex);
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
