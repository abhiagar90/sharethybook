/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.dbclasses;

import com.sharethyapp.helper.Messages;
import com.sharethyapp.helper.UtilitiesHelper;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author reshma
 */
public class WishList extends DB{

    public String insertIntoWishList(String isbn,String entryNumber) {
        openConnection();

        String insertNewMsgSQL = "insert into WishList values (?,?,?);";
        try {
            preparedStatement = conn.prepareStatement(insertNewMsgSQL);
            preparedStatement.setString(1, entryNumber);
            preparedStatement.setString(2, isbn);
            preparedStatement.setTimestamp(3, UtilitiesHelper.getCurrentTimeStamp());
                     
            int res = preparedStatement.executeUpdate();
            if(res!=1)
                return "Nothing inserted in wishlist table seems our mistake.";

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
}
