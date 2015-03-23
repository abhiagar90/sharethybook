/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.dbclasses;

import com.sharethyapp.helper.BookResult;

import com.sharethyapp.helper.PhysicalBooks;
import com.sharethyapp.helper.RateAndReview;
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
    
    public BookSearchQueryResult SearchBooks(String whereCond, int Mode) throws NoSuchAlgorithmException //returns null if exception
    {
        openConnection();
        
        String getSearchRes = null;
        
        if (Mode == 1) {
            if (whereCond.trim() != "") {
                getSearchRes = "select M.ISBN,M.Title,M.Year,M.Publisher,M.Rating,A.AuthorName from MasterBooks M,Authors A,BooksWrittenBy B where " + whereCond + " AND M.ISBN=B.ISBN AND B.AuthorID=A.AuthorID";
            } else {
                getSearchRes = "select M.ISBN,M.Title,M.Year,M.Publisher,M.Rating,A.AuthorName from MasterBooks M,Authors A,BooksWrittenBy B where M.ISBN=B.ISBN AND B.AuthorID=A.AuthorID";
            }
        } else {
            getSearchRes = whereCond;
        }
        
        try {
            preparedStatement = conn.prepareStatement(getSearchRes);
            ResultSet rs = preparedStatement.executeQuery();
            
            BookSearchQueryResult bookQueryRes = new BookSearchQueryResult();
            
            List<BookResult> bookRes = new ArrayList<BookResult>();
            
            BookResult book;
            
            while (rs.next()) {                
                book = new BookResult(rs.getString("isbn"), rs.getString("Title"), rs.getString("Year"), rs.getString("Publisher"), rs.getString("Rating"), rs.getString("AuthorName"));                
                bookRes.add(book);
            }            
            
            bookQueryRes.setBookRes(bookRes);
            bookQueryRes.setBookSearchQuery(getSearchRes);
            return bookQueryRes;
            
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
                temp.setYear(rs.getString("year"));
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
    
    private final String getAllReviewsRatesSQL = "select * from rating where isbn = ?";
    
    public List<RateAndReview> getAllReviewsRatesByISBN(String isbn) {
        List<RateAndReview> rateReviewList = null;
        
        openConnection();
        
        try {
            List<RateAndReview> temp = new LinkedList<RateAndReview>();
            preparedStatement = conn.prepareStatement(getAllReviewsRatesSQL);
            preparedStatement.setString(1, isbn);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                RateAndReview rr = new RateAndReview();
                rr.setEntrynumber(rs.getString("entrynumber"));
                rr.setRating(rs.getString("rating"));
                rr.setReview(rs.getString("review"));
                if (rr.getEntrynumber() != null) {
                    temp.add(rr);
                }                
            }
            if (!temp.isEmpty()) {
                rateReviewList = temp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return rateReviewList;
    }
    
    private final String getAllRatingCountSQL = "select count(*) count from rating where isbn = ?;";
    
    public String getAllRatingCountByISBN(String isbn) {
        String ratingCount = null;
        
        openConnection();
        
        try {
            String temp = null;
            preparedStatement = conn.prepareStatement(getAllRatingCountSQL);
            preparedStatement.setString(1, isbn);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                temp = rs.getString("count");
            }
            if (temp != null && !temp.isEmpty()) {
                ratingCount = temp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return ratingCount;
    }
    
    private final String getTop10BooksSql = "select * from masterbooks order by rating desc LIMIT 10;";
    
    public List<BookResult> getTop10Books() {
        openConnection();
        List<BookResult> bookRes = new ArrayList<BookResult>();
        try {
            preparedStatement = conn.prepareStatement(getTop10BooksSql);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                BookResult tempbook = new BookResult();
                tempbook.setIsbn(rs.getString("isbn"));
                tempbook.setPublisher(rs.getString("publisher"));
                tempbook.setRating(rs.getString("rating"));
                tempbook.setYear(rs.getString("year"));
                tempbook.setTitle(rs.getString("title"));
                bookRes.add(tempbook);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return bookRes;
    }
    
    private final String getTop10TransactedBooksSql = "select temp.isbn,Title,Aggre from (Select isbn,count(*) Aggre from"
            + " (select Bookid from transactions where Status='E') As booksTransacted, "
            + "books where books.bookid=bookstransacted.bookid group by isbn order by Aggre Desc LIMIT 10) as temp,"
            + "masterbooks where masterbooks.isbn=temp.isbn ;";
    
    public List<BookResult> getTop10TransactedBooks() {
        openConnection();
        List<BookResult> bookRes = new ArrayList<BookResult>();
        try {
            preparedStatement = conn.prepareStatement(getTop10TransactedBooksSql);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                BookResult tempbook = new BookResult();
                tempbook.setIsbn(rs.getString("isbn"));
                tempbook.setTitle(rs.getString("title"));
                tempbook.setCount(rs.getString("aggre")); 
                bookRes.add(tempbook);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return bookRes;
    }
    
    private final String getBookImageSQL= " select image from masterbooksimages where isbn=?;";
    public byte[] getImagefromISBN(String isbn) //returns null if exception
    {
        openConnection();
        
        byte[] photo = null;
        
        try {
            byte[] temp = null;
            preparedStatement = conn.prepareStatement(getBookImageSQL);
            preparedStatement.setString(1, isbn);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                temp = (rs.getBytes("image"));
                photo = temp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return photo;
        //if returned is null, then we have a problem
    }
    
}
