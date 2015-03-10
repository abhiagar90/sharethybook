/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.servlets;

import com.sharethyapp.dbclasses.UserTable;
import com.sharethyapp.dbclasses.UserTableDB;
import com.sharethyapp.helper.LoginHelper;
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
public class ProfileServlet extends HttpServlet {

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
            throws ServletException,IOException  {
            String entrynum=request.getParameter("entrynumber");
            UserTable user=new UserTableDB().getDetailsfromEntryNum(entrynum);
            
        if(user!=null)
        {
            request.setAttribute("user", user);
            request.setAttribute("typeUser", LoginHelper.getTypeOfUser(user.getTypeOfUser()));
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profile.jsp");
            dispatcher.forward(request, response);
            
            //TODO: profile.jsp books if he has all shown
            //Constraint for entrynumber type : can we write constraint!
            //inside database in capital so as to have consistency
            //Fill contact numbers and show use join!
            //Books fill with thier fancy ids! Phew!
            //Fill master book images if possible to show profile image
            //book profile page
            //will show all info about the book and images! 
            //and also which books avaliable to borrow.
            
            //TODO: may we need to not use materialized view!
            //may be it is not shadow copying the DB!
            //When sign up module done, check!
            
            
           //Search books by name check equalsignore case or substring
           //then session check reshma code
            
            //TODO: Do we need to use join for user page, book page, etc.?
        }
        else
        {
            //From now on, global error msgs in errorMsg
            request.setAttribute("errorMsg", "EntryNumber not Valid!<br/>");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
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
