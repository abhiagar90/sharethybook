/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.helper;

import com.sharethyapp.dbclasses.UserTable;
import com.sharethyapp.dbclasses.UserTableDB;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author abhishek
 */
public class UtilitiesHelper {

    public static String getMD5Hash(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UtilitiesHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        md.update(password.getBytes());
        byte[] digest = md.digest();

        StringBuffer sb1 = new StringBuffer();
        for (byte b : digest) {
            sb1.append(String.format("%02x", b & 0xff));
        }
        return sb1.toString();
    }

    public static String returnNullOrString(HttpServletRequest request, String param) {
        String paramString = request.getParameter(param);
        if (paramString == null) {
            return null;
        }
        if (paramString.trim().isEmpty()) {
            return null;
        } else {
            return paramString.trim();
        }
    }
    
    public static int getUserType(HttpServletRequest request)
    {
        String entrynumber = (String)request.getSession().getAttribute("entrynumber");
        UserTableDB utb = new UserTableDB();
        UserTable ut = utb.getDetailsfromEntryNum(entrynumber);
        return ut.getTypeOfUser();
    }

    public static java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }
}
