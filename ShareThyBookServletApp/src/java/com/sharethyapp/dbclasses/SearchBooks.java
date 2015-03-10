/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.dbclasses;

import com.sharethyapp.helper.BookResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author reshma
 */
public class SearchBooks extends DB {
    
     public List<BookResult> SearchBooks(String whereCond)  throws NoSuchAlgorithmException //returns null if exception
    {
        openConnection();
        
        String getSearchRes=null;
      
            if(whereCond.trim()!="")
            {
                getSearchRes = "select M.ISBN,M.Title,M.Year,M.Publisher,M.Rating,A.AuthorName from MasterBooks M,Authors A,BooksWrittenBy B where "+whereCond+" AND M.ISBN=B.ISBN AND B.AuthorID=A.AuthorID";
            }
            else
            {
                getSearchRes = "select M.ISBN,M.Title,M.Year,M.Publisher,M.Rating,A.AuthorName from MasterBooks M,Authors A,BooksWrittenBy B where M.ISBN=B.ISBN AND B.AuthorID=A.AuthorID";
            }
      
        try {
            preparedStatement = conn.prepareStatement(getSearchRes);
            ResultSet rs = preparedStatement.executeQuery();
                        
            List<BookResult> bookRes= new ArrayList<BookResult>();
            BookResult book;
            

                while(rs.next())  
                {  
                  book=new BookResult(rs.getString("isbn"),rs.getString("Title"),rs.getString("Year"),rs.getString("Publisher"),rs.getString("Rating"),rs.getString("AuthorName"));  
                  bookRes.add(book);
                }  
            
           return bookRes;
           
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            closeConnection();
        }
        
    }

}
