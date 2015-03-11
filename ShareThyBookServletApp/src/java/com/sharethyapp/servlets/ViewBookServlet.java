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
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author abhishek
 */
public class ViewBookServlet extends HttpServlet {

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
        String isbn = request.getParameter("isbn");
        //now we need to query the databse for the book
        BookResult book = new SearchBooks().getMasterBookDetailsByISBN(isbn);

        if (book != null) {
            book.setAuthorList(new SearchBooks().getAllAuthorsByISBN(isbn));
            //book.setReviewList(new SearchBooks().getAllReviewsByISBN(isbn));
            book.setRateReviewList(new SearchBooks().getAllReviewsRatesByISBN(isbn));
            book.setNumOfRatings(new SearchBooks().getAllRatingCountByISBN(isbn));
            
            //now get the physical copies of the books from the tables
            List<PhysicalBooks> physicalList = new PhysicalBooksDB().getPhysicalBooksDetailsByISBN(isbn);
            request.setAttribute("masterbook", book);
            if (physicalList != null && !physicalList.isEmpty()) {
                request.setAttribute("physicalList", physicalList);
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/viewbook.jsp");
            dispatcher.forward(request, response);

        } else {
            //From now on, global error msgs in errorMsg
            //will see about redirection
            request.setAttribute("errorMsg", "ISBN not Valid!<br/>");
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
