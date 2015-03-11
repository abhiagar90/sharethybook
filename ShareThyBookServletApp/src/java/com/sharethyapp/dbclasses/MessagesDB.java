/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.dbclasses;

import com.sharethyapp.helper.Messages;
import com.sharethyapp.helper.RateAndReview;
import com.sharethyapp.helper.UtilitiesHelper;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Utilities;

/**
 *
 * @author abhishek
 */
public class MessagesDB extends DB{
    
    private static final String insertNewMsgSQL = "insert into messages(fromid, toid, date, message, status) values (?,?,?,?,?);";
    
    public String insertNewRating(Messages msg) {
        openConnection();

        try {
            preparedStatement = conn.prepareStatement(insertNewMsgSQL);
            preparedStatement.setString(1, msg.getFromid());
            preparedStatement.setString(2, msg.getToid());
            preparedStatement.setTimestamp(3, UtilitiesHelper.getCurrentTimeStamp());
            preparedStatement.setString(4, msg.getMessage());
            preparedStatement.setBoolean(5, false);
            
            int res = preparedStatement.executeUpdate();
            if(res!=1)
                return "Nothing inserted in message table seems our mistake.";

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
