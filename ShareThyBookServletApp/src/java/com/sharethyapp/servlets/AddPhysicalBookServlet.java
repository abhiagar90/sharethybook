/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.servlets;

import com.sharethyapp.dbclasses.PhysicalBooksDB;
import com.sharethyapp.dbclasses.SearchBooks;
import com.sharethyapp.helper.BookResult;
import com.sharethyapp.helper.PhysicalBooks;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author reshma
 */
public class AddPhysicalBookServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
            String isbn=request.getParameter("ISBN");
         
            BookResult book = new SearchBooks().getMasterBookDetailsByISBN(isbn);

            if (book != null) {
                book.setAuthorList(new SearchBooks().getAllAuthorsByISBN(isbn));
                book.setRateReviewList(new SearchBooks().getAllReviewsRatesByISBN(isbn));
                book.setNumOfRatings(new SearchBooks().getAllRatingCountByISBN(isbn));

                request.setAttribute("masterbook", book);
           // }
               //if(new PhysicalBooksDB().InsertPhysicalBook(request.getParameter("ISBN"),request.getSession().getAttribute("entrynumber").toString(),"G")) 
           // {
               
            

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/contributeBook.jsp?ISBN="+isbn);
                dispatcher.forward(request, response);
            }
            else
            {
               // request.getSession().setAttribute("Query", rs.getBookSearchQuery());

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/contributeBook.jsp?ISBN="+isbn);
                dispatcher.forward(request, response);
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
