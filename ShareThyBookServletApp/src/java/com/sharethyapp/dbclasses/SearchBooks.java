/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.dbclasses;

import com.sharethyapp.helper.BookResult;
import com.sharethyapp.helper.BookSearchQueryResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author reshma
 */
public class SearchBooks extends DB {
<<<<<<< HEAD
    
     public BookSearchQueryResult SearchBooks(String whereCond,int Mode)  throws NoSuchAlgorithmException //returns null if exception
    {
        openConnection();
        
        String getSearchRes=null;
      
        if(Mode==1)
        {
            if(whereCond.trim()!="")
            {
                getSearchRes = "select M.ISBN,M.Title,M.Year,M.Publisher,M.Rating,A.AuthorName from MasterBooks M,Authors A,BooksWrittenBy B where "+whereCond+" AND M.ISBN=B.ISBN AND B.AuthorID=A.AuthorID";
            }
            else
            {
                getSearchRes = "select M.ISBN,M.Title,M.Year,M.Publisher,M.Rating,A.AuthorName from MasterBooks M,Authors A,BooksWrittenBy B where M.ISBN=B.ISBN AND B.AuthorID=A.AuthorID";
            }
        }
        else
        {
            getSearchRes=whereCond;
        }
      
        try {
            preparedStatement = conn.prepareStatement(getSearchRes);
            ResultSet rs = preparedStatement.executeQuery();
            
            BookSearchQueryResult bookQueryRes=new BookSearchQueryResult();
                        
            List<BookResult> bookRes= new ArrayList<BookResult>();
            
            BookResult book;
            

                while(rs.next())  
                {  
                  book=new BookResult(rs.getString("isbn"),rs.getString("Title"),rs.getString("Year"),rs.getString("Publisher"),rs.getString("Rating"),rs.getString("AuthorName"));  
                  bookRes.add(book);
                }  
            
           bookQueryRes.setBookRes(bookRes);
           bookQueryRes.setBookSearchQuery(getSearchRes);
           return bookQueryRes;
           
=======

    public List<BookResult> SearchBooks(String whereCond) throws NoSuchAlgorithmException //returns null if exception
    {
        openConnection();

        String getSearchRes = null;

        if (whereCond.trim() != "") {
            getSearchRes = "select M.ISBN,M.Title,M.Year,M.Publisher,M.Rating,A.AuthorName from MasterBooks M,Authors A,BooksWrittenBy B where " + whereCond + " AND M.ISBN=B.ISBN AND B.AuthorID=A.AuthorID";
        } else {
            getSearchRes = "select M.ISBN,M.Title,M.Year,M.Publisher,M.Rating,A.AuthorName from MasterBooks M,Authors A,BooksWrittenBy B where M.ISBN=B.ISBN AND B.AuthorID=A.AuthorID";
        }

        try {
            preparedStatement = conn.prepareStatement(getSearchRes);
            ResultSet rs = preparedStatement.executeQuery();

            List<BookResult> bookRes = new ArrayList<BookResult>();
            BookResult book;

            while (rs.next()) {
                book = new BookResult(rs.getString("isbn"), rs.getString("Title"), rs.getString("Year"), rs.getString("Publisher"), rs.getString("Rating"), rs.getString("AuthorName"));
                bookRes.add(book);
            }

            return bookRes;

>>>>>>> f904c7b2610e4a242455cdbe49e006b5c669ca06
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            closeConnection();
        }

    }

    private final String getMasterBookDetailsSQL = "select * from MasterBooks where ISBN=?;";

    public BookResult getMasterBookDetailsByISBN(String isbn) {

        openConnection();

        BookResult book = null;

        try {
            BookResult temp = new BookResult();
            preparedStatement = conn.prepareStatement(getMasterBookDetailsSQL);
            preparedStatement.setString(1, isbn);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                temp.setIsbn(rs.getString("isbn"));
                temp.setPublisher(rs.getString("publisher"));
                temp.setRating(rs.getString("rating"));
                temp.setAuthors(rs.getString("year"));
                temp.setTitle(rs.getString("title"));
                book = temp;
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return book;
    }

    private final String getAuthorsDetailsSQL = "select a.authorname from authors a, bookswrittenby b "
            + "where a.authorid=b.authorid and b.isbn=?;";

    public List<String> getAllAuthorsByISBN(String isbn) {
        List<String> authorList = null;

        openConnection();

        try {
            List<String> temp = new LinkedList<String>();
            preparedStatement = conn.prepareStatement(getAuthorsDetailsSQL);
            preparedStatement.setString(1, isbn);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                temp.add(rs.getString("authorname"));
            }
            if (!temp.isEmpty()) {
                authorList = temp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return authorList;
    }

    private final String getAllReviewsSQL = "select review from rating where isbn = ?; and review is not null";

    public List<String> getAllReviewsByISBN(String isbn) {
        List<String> reviewList = null;

        openConnection();

        try {
            List<String> temp = new LinkedList<String>();
            preparedStatement = conn.prepareStatement(getAllReviewsSQL);
            preparedStatement.setString(1, isbn);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                temp.add(rs.getString("review"));
            }
            if (!temp.isEmpty()) {
                reviewList = temp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return reviewList;
    }

    private final String getAllRatingCountSQL = "select count(*) count from rating where isbn = ?;";

    public String getAllRatingCountByISBN(String isbn) {
        String ratingCount = null;

        openConnection();

        try {
            String temp =  null;
            preparedStatement = conn.prepareStatement(getAllRatingCountSQL);
            preparedStatement.setString(1, isbn);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                temp = rs.getString("review");
            }
            if(temp!=null && !temp.isEmpty())
                ratingCount = temp;
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return ratingCount;
    }

}
