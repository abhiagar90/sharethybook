/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.servlets;

import com.sharethyapp.dbclasses.TransactionDB;
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
 * @author reshma
 */
public class AcceptRejectServlet extends HttpServlet {

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
        String transID = request.getParameter("tid");
        String status = request.getParameter("status");
        String cond = UtilitiesHelper.returnNullOrString(request, "cond");
        String entrynum = (String) request.getSession().getAttribute("entrynumber");

        String sqlOutput;

        TransactionDB tb = new TransactionDB();

        if (status.equals("E") && cond == null) {
            request.setAttribute("tid", transID);
            request.setAttribute("status", status);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/bookBorrowed.jsp");
            dispatcher.forward(request, response);
        } else {
            sqlOutput = tb.acceptRejectTransaction(transID, status, cond);
            if (sqlOutput.equals("true")) {
                request.setAttribute("infoMsg", "Status updated for book while accepting/rejecting. <br/>");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profile.do?entrynumber=" + entrynum);
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("error", "Error in accepting/rejecting transaction <br> " + sqlOutput);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profile.do?entrynumber=" + entrynum);
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
