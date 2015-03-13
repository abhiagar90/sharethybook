/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.servlets;

import com.sharethyapp.helper.Messages;
import com.sharethyapp.dbclasses.MessagesDB;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
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
public class SuggestBookServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        String isbn=request.getParameter("ISBN").trim();
        String Title=request.getParameter("Title").trim();
        String Year=request.getParameter("Year").trim();
        String Author=request.getParameter("Author").trim();
        String Publisher=request.getParameter("Publisher").trim();
        
        if (isbn.isEmpty()&&Title.isEmpty()&& Author.isEmpty())
        {
            request.setAttribute("Error", "Information provided for book is not enough.Please Provide either isbn or title and author name.");

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/suggestBook.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            Messages msg=new Messages();
            msg.setFromid(request.getSession().getAttribute("entrynumber").toString());
            msg.setToid("MODR10001");
            msg.setMessage("Book Suggessted :: ISBN-"+isbn+" Name- "+Title+" Author- "+Author+" Publisher- "+ Publisher+" Year-"+Year);
            String res= new MessagesDB().insertNewMessage(msg);
            
            if(res.trim().equals("true"))
            {
                 request.setAttribute("Message", "The suggestion for book is sent to moderator. You'll be notified.");

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/suggestBook.jsp");
                dispatcher.forward(request, response);
            }
            else
            {
                  request.setAttribute("error", res);

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/suggestBook.jsp");
                dispatcher.forward(request, response);
            }
        }
        
        } finally {
  
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
