/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.dbclasses;

import com.sharethyapp.helper.Messages;
import com.sharethyapp.helper.UtilitiesHelper;
import com.sharethyapp.helper.WishList;
import com.sharethyapp.helper.WishListAggregated;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author reshma
 */
public class WishListDB extends DB {

    public String insertIntoWishList(String isbn, String entryNumber) {
        openConnection();

        String insertNewMsgSQL = "insert into WishList values (?,?,?);";
        try {
            preparedStatement = conn.prepareStatement(insertNewMsgSQL);
            preparedStatement.setString(1, entryNumber);
            preparedStatement.setString(2, isbn);
            preparedStatement.setTimestamp(3, UtilitiesHelper.getCurrentTimeStamp());

            int res = preparedStatement.executeUpdate();
            if (res != 1) {
                return "Nothing inserted in wishlist table seems our mistake.";
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
            return "SQL " + ex.getMessage();
        } catch (Exception ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
            return "NOT SQL : " + ex.getMessage();
        } finally {
            closeConnection();
        }
        return "true";
    }

    String getAllWishesAggregatedSQL = "select isbn, count(*) count from wishlist group by isbn order by count desc;";

    public List<WishListAggregated> getAllWishesAggregated() {

        List<com.sharethyapp.helper.WishListAggregated> booksWished = new ArrayList<com.sharethyapp.helper.WishListAggregated>();

        openConnection();

        try {

            preparedStatement = conn.prepareStatement(getAllWishesAggregatedSQL);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                com.sharethyapp.helper.WishListAggregated wish = new com.sharethyapp.helper.WishListAggregated();
                wish.setIsbn(rs.getString("isbn"));
                wish.setCount(rs.getString("count"));

                booksWished.add(wish);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return booksWished;
    }
    
    String getAllWishesSQL = "select * from wishlist order by isbn asc, date desc;";

    public List<WishList> getAllWishes() {

        List<com.sharethyapp.helper.WishList> booksWished = new ArrayList<com.sharethyapp.helper.WishList>();

        openConnection();

        try {

            preparedStatement = conn.prepareStatement(getAllWishesSQL);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                com.sharethyapp.helper.WishList wish = new com.sharethyapp.helper.WishList();
                wish.setIsbn(rs.getString("isbn"));
                wish.setEntrynumber(rs.getString("entrynumber"));
                wish.setDate(rs.getDate("date"));

                booksWished.add(wish);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return booksWished;
    }
}
