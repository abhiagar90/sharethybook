/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.dbclasses;

import com.sharethyapp.helper.RateAndReview;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abhishek
 */
public class RateAndReviewDB extends DB {
    private static final String insertRateSQL = "insert into rating values(?,?,?,?);";
     
    public String insertNewRating(RateAndReview newrow) {
        openConnection();

        try {
            preparedStatement = conn.prepareStatement(insertRateSQL);
            preparedStatement.setString(1, newrow.getIsbn());
            preparedStatement.setString(2, newrow.getEntrynumber());
            preparedStatement.setInt(3, Integer.parseInt(newrow.getRating()));
            preparedStatement.setString(4, newrow.getReview());
            
            int res = preparedStatement.executeUpdate();
            if(res!=1)
                return "Nothing inserted in rating table seems our mistake.";

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
