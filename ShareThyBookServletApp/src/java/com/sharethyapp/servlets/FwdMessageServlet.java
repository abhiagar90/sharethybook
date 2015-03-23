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
public class FwdMessageServlet extends HttpServlet {

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
        String modid = UtilitiesHelper.returnNullOrString(request, "modid");
        String content = UtilitiesHelper.returnNullOrString(request, "content");
        String fromid = "ADMIN";
        boolean err = true;

        Messages m = new Messages();
        m.setFromid(fromid);
        m.setToid(modid);
        m.setMessage(content);
        String firstOutput = new MessagesDB().insertNewMessage(m);
        String secondOutput = null;
        String msgid = UtilitiesHelper.returnNullOrString(request, "mid"); //for deletion
        
        if (firstOutput.equals("true")) {
            
            secondOutput = new MessagesDB().deleteMessage(msgid);
            if (secondOutput.equals("true")) {
                err = false;
            }
        }
        
        if(!err)
        {
            request.setAttribute("infoMsg", "Msg Forwarded successfully! <br/>");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/allmsgsview.do");
            dispatcher.forward(request, response);
        }
        else
        {
            request.setAttribute("error", "Msg NOT Forwarded!! <br/>");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/readmsg.do?mid="+msgid);
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
