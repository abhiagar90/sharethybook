/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.servlets;

import com.sharethyapp.dbclasses.UserTable;
import com.sharethyapp.dbclasses.UserTableDB;
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
public class EditProfileServlet extends HttpServlet {

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
        UserTable updatedUser = new UserTable();
        updatedUser.setFirstname(UtilitiesHelper.returnNullOrString(request, "firstName"));
        updatedUser.setLastname(UtilitiesHelper.returnNullOrString(request, "lastName"));

        String entryNumber = UtilitiesHelper.returnNullOrString(request, "EntryNumber");
        if (entryNumber != null) {
            entryNumber = entryNumber.toUpperCase();
        }
        updatedUser.setEntrynumber(entryNumber);

        String hostelerRadio = UtilitiesHelper.returnNullOrString(request, "Hosteler");
        boolean isHostler = false;
        if (hostelerRadio != null && hostelerRadio.equals("choice-1")) {
            isHostler = true;
        }
        updatedUser.setIsHosteler(isHostler);
        updatedUser.setHouseNo(UtilitiesHelper.returnNullOrString(request, "HouseNo"));
        updatedUser.setStreetNo(UtilitiesHelper.returnNullOrString(request, "StreetNo"));
        updatedUser.setCity(UtilitiesHelper.returnNullOrString(request, "City"));
        updatedUser.setState(UtilitiesHelper.returnNullOrString(request, "State"));
        updatedUser.setPincode(UtilitiesHelper.returnNullOrString(request, "PinCode"));
        updatedUser.setEmailId(UtilitiesHelper.returnNullOrString(request, "EmailID"));

        String sqlOutput = new UserTableDB().updateOldUser(updatedUser);
        
        if (sqlOutput.equals("true")) {
            request.setAttribute("infoMsg", "Profile Successfully updated! <br/>");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profile.do?entrynumber="+updatedUser.getEntrynumber());
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("error", "Some of the entries violate our DB constraints. Please check instructions above. <br> " + sqlOutput);
            updatedUser = new UserTableDB().getDetailsfromEntryNum(updatedUser.getEntrynumber());
            request.setAttribute("newUser", updatedUser);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/editProfile.jsp");
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
