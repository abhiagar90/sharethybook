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
import com.sharethyapp.helper.UtilitiesHelper;
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
public class InsertBookDetails extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
        try {
            String isbn=request.getParameter("ISBN");
            
             BookResult book = new SearchBooks().getMasterBookDetailsByISBN(isbn);

            if (book != null) {
                book.setAuthorList(new SearchBooks().getAllAuthorsByISBN(isbn));
                //book.setReviewList(new SearchBooks().getAllReviewsByISBN(isbn));
                book.setRateReviewList(new SearchBooks().getAllReviewsRatesByISBN(isbn));
                if (book.getRateReviewList() != null) {
                    book.setNumOfRatings(""+book.getRateReviewList().size());
                }
                else
                {
                    book.setNumOfRatings("0");
                }

                //now get the physical copies of the books from the tables
                List<PhysicalBooks> physicalList = new PhysicalBooksDB().getPhysicalBooksDetailsByISBN(isbn);
                request.setAttribute("masterbook", book);
                if (physicalList != null && !physicalList.isEmpty()) {
                    request.setAttribute("physicalList", physicalList);
                }
               
                
                String hostelerRadio = UtilitiesHelper.returnNullOrString(request, "Condition");
                String condition = hostelerRadio.toString();
               

                if(new PhysicalBooksDB().InsertPhysicalBook(isbn,request.getSession().getAttribute("entrynumber").toString(),condition)) 
                {
                     request.setAttribute("Message", "The Book is now added to library. Thank you for contributing.");

                      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/contributeBook.jsp");
                      dispatcher.forward(request, response);
                }
                else
                {
                    request.setAttribute("Error", "Error in adding Book.Please try later to add book.");

                      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/contributeBook.jsp");
                      dispatcher.forward(request, response);
                }
            }
        }
        catch(Exception e)
        {
            request.setAttribute("Error", "Error in adding Book.Please try later to add book."+e.getMessage());
                
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/contributeBook.jsp");
                dispatcher.forward(request, response);
        }
        finally {
  
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
