/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.servlets;

import com.sharethyapp.dbclasses.MessagesDB;
import com.sharethyapp.helper.Messages;
import com.sharethyapp.helper.UtilitiesHelper;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author abhishek
 */
public class MessageServlet extends HttpServlet {

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
        Messages msg = new Messages();
        msg.setFromid(UtilitiesHelper.returnNullOrString(request, "fromid"));
        msg.setToid(UtilitiesHelper.returnNullOrString(request, "toid"));
        msg.setMessage(UtilitiesHelper.returnNullOrString(request, "msg"));
        String sqlOutput = new MessagesDB().insertNewRating(msg);

        if (sqlOutput.equals("true")) {
            request.setAttribute("infoMsg", "Sent message successfully");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profile.do?entrynumber="
                    + msg.getToid());
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("error", "Some of the entries violate our DB constraints. Please check instructions above. <br> " + sqlOutput);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profile.do?entrynumber="
                    + msg.getToid());
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
