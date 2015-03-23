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
import javax.swing.text.Utilities;

/**
 *
 * @author reshma
 */
public class ChangeUserTypeServlet extends HttpServlet {

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
        String entrynumber = UtilitiesHelper.returnNullOrString(request, "entrynumber");
        String oldType = UtilitiesHelper.returnNullOrString(request, "oldtype");
        String newType = null;
        if(oldType.equals("Moderator"))
            newType = "3";
        else
            newType = "2";
        String sqloutput = new UserTableDB().updateUserType(entrynumber, Integer.parseInt(newType));
        if(sqloutput.equals("true"))
        {
            request.setAttribute("infoMsg", "User type updated! <br/>");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profile.do?entrynumber="+entrynumber);
            dispatcher.forward(request, response);
        }
        else
        {
            request.setAttribute("error", "User type NOT updated! <br/>");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profile.do?entrynumber="+entrynumber);
            dispatcher.forward(request, response);
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
