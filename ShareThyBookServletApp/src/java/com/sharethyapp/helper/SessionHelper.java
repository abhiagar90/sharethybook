/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.helper;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author abhishek
 */
public class SessionHelper {
    public static boolean isLoggedIn(HttpServletRequest request)
    {
        return (request.getSession().getAttribute("entrynumber") != null
                        && !request.getSession().getAttribute("entrynumber").equals("NA"));
    }    
}
