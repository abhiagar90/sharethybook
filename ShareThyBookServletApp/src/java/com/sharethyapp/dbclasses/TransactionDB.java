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
import javax.servlet.RequestDispatcher;

/**
 *
 * @author reshma
 */
public class TransactionDB  extends DB{
    
       public String startTransaction(String fromID,String toID,long bookID) {
        openConnection();

        String insertTransactionSQL = "insert into Transactions(FromID, ToID, BookID, TransStartDate, LastUpdate,Status,BookCondition) values (?,?,?,?,?,?,?);";
        
        try {
            preparedStatement = conn.prepareStatement(insertTransactionSQL);
            preparedStatement.setString(1, fromID);
            preparedStatement.setString(2,toID);
            preparedStatement.setLong(3,bookID);
            preparedStatement.setTimestamp(4, UtilitiesHelper.getCurrentTimeStamp());
            preparedStatement.setTimestamp(5, UtilitiesHelper.getCurrentTimeStamp());
            preparedStatement.setString(6, "R");
            preparedStatement.setString(7, null);
            
            int res = preparedStatement.executeUpdate();
            if(res!=1)
                return "Nothing inserted in transaction table seems our mistake.";
            
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
