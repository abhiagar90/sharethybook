/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abhishek
 */
public class Constants {

    public static final String driver = "org.postgresql.Driver";
    public static final String dburl = "jdbc:postgresql://localhost:5432/library";
    public static final String uname = "postgres";
    public static final String password = "password";
    public static final String signUpDetials = "1. User already with the primary key.\n"
            + "2. Email id not of iitd \n" 
            + "3. Pincode pattern wrong \n" 
            + "4. ";

}
