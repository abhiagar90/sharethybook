/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.servlets;

import com.sharethyapp.dbclasses.UserTableDB;
import com.sharethyapp.helper.UtilitiesHelper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author abhishek
 */
public class ChangePasswordServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oldpass = UtilitiesHelper.returnNullOrString(request, "oldpass");
        String newpass = UtilitiesHelper.returnNullOrString(request, "newpass");
        String repass = UtilitiesHelper.returnNullOrString(request, "repass");

        if (oldpass == null || newpass == null || repass == null || !newpass.equals(repass)) {
            request.setAttribute("error", "Passwords do not match or empty!");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/changePassword.jsp");
            dispatcher.forward(request, response);
        } else {
            String oldhash = UtilitiesHelper.getMD5Hash(oldpass);
            String newhash = UtilitiesHelper.getMD5Hash(newpass);

            String sqlOutput = new UserTableDB().updatePassword((String) request.getSession().getAttribute("entrynumber"),
                    newhash, oldhash);
            System.out.println("OUTTTTTTTTTTTTTTTTTT: " + sqlOutput);
            
            if (sqlOutput.equals("true")) {
                request.setAttribute("infoMsg", "Password successfully changed");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profile.do?entrynumber="
                        + (String) request.getSession().getAttribute("entrynumber"));
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("error", "Some of the entries violate our DB constraints. Please check instructions above. <br> " + sqlOutput);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/changePassword.jsp");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
