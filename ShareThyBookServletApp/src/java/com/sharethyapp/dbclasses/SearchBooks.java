/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.dbclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author reshma
 */
public class SearchBooks extends DB {
    
     public ResultSet SearchBooks(int Mode,String val)  throws NoSuchAlgorithmException //returns null if exception
    {
        openConnection();
        
        String getSearchRes=null;
        if(Mode==1)
        {
            if(val.trim()!="")
            {
                getSearchRes = "select ISBN,Title,Year,Publisher,Rating from MasterBooks where Title ILIKE ?";
                val=proccessVal(val);
            }
            else
            {
                getSearchRes = "select ISBN,Title,Year,Publisher,Rating from MasterBooks";
            }
        }
        try {
            preparedStatement = conn.prepareStatement(getSearchRes);
            preparedStatement.setString(1, val);
            ResultSet rs = preparedStatement.executeQuery();
           return rs;
           
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            closeConnection();
        }
        
    }

    private String proccessVal(String val) {
        String[] strArray=val.split(" ");
        String res="%";
        for (int i=0;i<strArray.length;i++)
            res=res+strArray[i].trim()+"%";
        return res;
    }
}
