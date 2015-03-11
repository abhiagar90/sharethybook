/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.dbclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author reshma
 */
public class SignUpDB extends DB{
    
    public boolean newUser(String firstName,String lastName,String entryNumber,
              String password,      
              Boolean Hostler,     
              String houseNo,              
              String streetNo,String city,              
              String state,
              String pinCode, 
              String emailID) throws NoSuchAlgorithmException //returns null if exception
    {
        openConnection();

        String hashPassword = hashPassword(password);
        
        File file = new File("Anonymous.png");
        FileInputStream fis=null;
        try {
            fis = new FileInputStream(file);
        }
        catch (FileNotFoundException e) {
            System.out.println("the Anonymous File Does not exist.Please Correct Its Location");
        }
        catch(Exception e)
        {
            System.out.println("Exception in reading file :: "+e.getMessage()+" "+ e.getStackTrace());
        }
        
        String prepQuery="Insert into UserTable Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            preparedStatement = conn.prepareStatement(prepQuery);
            preparedStatement.setString(1, entryNumber);
            preparedStatement.setString(2, hashPassword);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, lastName);
            preparedStatement.setBoolean(5,Hostler);
            preparedStatement.setString(6, houseNo);
            preparedStatement.setString(7, streetNo);
            preparedStatement.setString(8, city);
            preparedStatement.setString(9, state);
            preparedStatement.setString(10, pinCode);
            preparedStatement.setString(11, emailID);
            preparedStatement.setInt(12, 3);
            preparedStatement.setInt(13, 0);
            preparedStatement.setInt(14, 0);
            preparedStatement.setInt(15, 0);
            preparedStatement.setBinaryStream(16, fis, (int)file.length());
            int res = preparedStatement.executeUpdate();
            return true;
           
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
        
    }
    
    private String hashPassword(String pass) throws NoSuchAlgorithmException
    {
   
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pass.getBytes());
        byte[] digest = md.digest();

        StringBuffer sb1 = new StringBuffer();
        for (byte b : digest) {
                sb1.append(String.format("%02x", b & 0xff));
           }
    return sb1.toString();
    }
}
