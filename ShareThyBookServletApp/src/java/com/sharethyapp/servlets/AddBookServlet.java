/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.servlets;

import com.sharethyapp.dbclasses.SearchBooks;
import com.sharethyapp.helper.BookResult;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
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
public class AddBookServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, SQLException {

        String bookName = request.getParameter("bookName").trim();
        String authorName=request.getParameter("authorName").trim();
        String publisherName=request.getParameter("publisherName").trim();
        String isbn=request.getParameter("ISBN");
        try{
            
        
        String searchCond="";
        if(bookName!="")
        {
            searchCond=" Title ILIKE '"+proccessVal(bookName)+"'";
        }
        if(authorName!="")
        {
            if(searchCond!="")
                searchCond=searchCond+" AND AuthorName ILIKE '"+proccessVal(authorName)+"'";
            else
                searchCond=" AuthorName ILIKE '"+proccessVal(authorName)+"'";
        }
        if(publisherName!="")
        {
              if(searchCond!="")
                searchCond=searchCond+" AND Publisher ILIKE '"+proccessVal(publisherName)+"'";
            else
                searchCond=" Publisher ILIKE '"+proccessVal(publisherName)+"'";
        }
        int  toYear;
        int fromYear;
        
         if(isbn!="")
        {
              if(searchCond!="")
                searchCond=searchCond+" AND M.ISBN ILIKE '"+proccessVal(isbn)+"'";
            else
                searchCond=" M.ISBN ILIKE '"+proccessVal(isbn)+"'";
        }
         
           if(request.getParameter("fromYear").trim()!="")
        {
            fromYear=Integer.parseInt(request.getParameter("fromYear"));
              if(searchCond!="")
                searchCond=searchCond+" AND Year >= "+fromYear;
            else
                searchCond=" Year >="+fromYear;
        }
           if(request.getParameter("toYear").trim()!="")
        {
            toYear=Integer.parseInt(request.getParameter("toYear"));
              if(searchCond!="")
                searchCond=searchCond+" AND Year <= "+toYear;
            else
                searchCond=" Year <= "+toYear;
        }
        
        List<BookResult> rs = new SearchBooks().SearchBooks(searchCond);
        request.setAttribute("listOfBooks", rs);
       

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/addbook.jsp");
        dispatcher.forward(request, response);
        }
        catch(Exception e)
        {
            request.setAttribute("error", "Error Occurred While Submitting Search Request."+e.getMessage());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/addbook.jsp");
            dispatcher.forward(request, response);
        }
    }
    
      private String proccessVal(String val) {
        String[] strArray=val.split(" ");
        String res="%";
        for (int i=0;i<strArray.length;i++)
            res=res+strArray[i].trim()+"%";
        return res;
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
            Logger.getLogger(AddBookServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddBookServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddBookServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddBookServlet.class.getName()).log(Level.SEVERE, null, ex);
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
