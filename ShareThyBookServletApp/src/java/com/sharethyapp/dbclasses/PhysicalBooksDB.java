/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.dbclasses;

import com.sharethyapp.helper.PhysicalBooks;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abhishek
 */
public class PhysicalBooksDB extends DB {

    private final String getOwnedBooksDetailsSQL = "select * from books where ownerid=?;";

    public List<PhysicalBooks> getOwnedBooksDetails(String ownerid) {
        List<PhysicalBooks> bookOwnList = null;

        openConnection();

        try {
            List<PhysicalBooks> temp = new LinkedList<PhysicalBooks>();
            preparedStatement = conn.prepareStatement(getOwnedBooksDetailsSQL);
            preparedStatement.setString(1, ownerid);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                PhysicalBooks book = new PhysicalBooks();
                book.setBookidPhysical(rs.getString("bookid"));
                book.setHolderid(rs.getString("holderid"));
                book.setIsbn(rs.getString("isbn"));
                book.setOwnerid(rs.getString("ownerid"));
                book.setHoldingdate(rs.getString("holdingdate"));
                book.setLastCondition(rs.getString("lastcondition"));
                if (book.getIsbn() != null) {
                    temp.add(book);
                }
            }
            if (!temp.isEmpty()) {
                bookOwnList = temp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return bookOwnList;
    }

    private final String getHavingBooksDetailsSQL = "select * from books where holderid=?;";

    public List<PhysicalBooks> getHavingBooksDetails(String holderid) {
        List<PhysicalBooks> booksHavingList = null;

        openConnection();

        try {
            List<PhysicalBooks> temp = new LinkedList<PhysicalBooks>();
            preparedStatement = conn.prepareStatement(getHavingBooksDetailsSQL);
            preparedStatement.setString(1, holderid);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                PhysicalBooks book = new PhysicalBooks();
                book.setBookidPhysical(rs.getString("bookid"));
                book.setHolderid(rs.getString("holderid"));
                book.setIsbn(rs.getString("isbn"));
                book.setOwnerid(rs.getString("ownerid"));
                book.setHoldingdate(rs.getString("holdingdate"));
                book.setLastCondition(rs.getString("lastcondition"));
                if (book.getIsbn() != null) {
                    temp.add(book);
                }
            }
            if (!temp.isEmpty()) {
                booksHavingList = temp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return booksHavingList;
    }

    private final String getPhysicalDetailsSQL = "select * from books where isbn=?;";

    public List<PhysicalBooks> getPhysicalBooksDetailsByISBN(String isbn) {
        List<PhysicalBooks> bookList = null;

        openConnection();

        try {
            List<PhysicalBooks> temp = new LinkedList<PhysicalBooks>();
            preparedStatement = conn.prepareStatement(getPhysicalDetailsSQL);
            preparedStatement.setString(1, isbn);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                PhysicalBooks book = new PhysicalBooks();
                book.setBookidPhysical(rs.getString("bookid"));
                book.setHolderid(rs.getString("holderid"));
                book.setIsbn(rs.getString("isbn"));
                book.setOwnerid(rs.getString("ownerid"));
                book.setHoldingdate(rs.getString("holdingdate"));
                book.setLastCondition(rs.getString("lastcondition"));
                if (book.getIsbn() != null) {
                    temp.add(book);
                }
            }
            if (!temp.isEmpty()) {
                bookList = temp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return bookList;
    }
}
