/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.helper;

import com.sharethyapp.dbclasses.LoginDB;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author abhishek
 */
public class LoginHelper {

    public static boolean verifyLogin(String entryNum, String Password) {
        String fromDB = new LoginDB().getPassword(entryNum);
        if (fromDB == null) {
            return false;
        } else {
            return (UtilitiesHelper.getMD5Hash(Password)).equals(fromDB);
        }
    }

    public static String getTypeOfUser(int type) {
        String userType;
        switch (type) {

            case 1:
                userType = "Admin";
                break;
            case 2:
                userType = "Moderator";
                break;
            case 3:
                userType = "Reader";
                break;
            default: userType = "Anonymous";
        }
        return userType;
    }
    
    public static boolean isLoggedIn(HttpServletRequest request)
    {
        return (request.getSession().getAttribute("entrynumber") != null
                        && !request.getSession().getAttribute("entrynumber").equals("NA"));
    } 

}
