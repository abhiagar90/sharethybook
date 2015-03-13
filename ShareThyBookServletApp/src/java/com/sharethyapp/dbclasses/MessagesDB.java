/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.dbclasses;

import com.sharethyapp.helper.Messages;
import com.sharethyapp.helper.UtilitiesHelper;
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
public class MessagesDB extends DB {

    private static final String insertNewMsgSQL = "insert into messages(fromid, toid, date, message, status) values (?,?,?,?,?);";

    public String insertNewMessage(Messages msg) {
        openConnection();

        try {
            preparedStatement = conn.prepareStatement(insertNewMsgSQL);
            preparedStatement.setString(1, msg.getFromid());
            preparedStatement.setString(2, msg.getToid());
            preparedStatement.setTimestamp(3, UtilitiesHelper.getCurrentTimeStamp());
            preparedStatement.setString(4, msg.getMessage());
            preparedStatement.setBoolean(5, false);

            int res = preparedStatement.executeUpdate();
            if (res != 1) {
                return "Nothing inserted in message table seems our mistake.";
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

    private static String receivedMsgsSQL = "select * from messages where toid=? order by status, date desc;";

    public List<Messages> getReceivedMsgsByEntryNumber(String toEntryStringNum) {

        List<Messages> recevdMsgList = null;

        openConnection();

        try {
            List<Messages> temp = new LinkedList<Messages>();
            preparedStatement = conn.prepareStatement(receivedMsgsSQL);
            preparedStatement.setString(1, toEntryStringNum);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Messages msg = new Messages();
                msg.setMessageid(rs.getString("messageid"));
                msg.setDate(rs.getString("date"));
                msg.setFromid(rs.getString("fromid"));
                msg.setStatus(rs.getBoolean("status"));
                msg.setMessage(rs.getString("message"));
                if (msg.getMessageid() != null) {
                    temp.add(msg);
                }
            }
            if (!temp.isEmpty()) {
                recevdMsgList = temp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return recevdMsgList;
    }

    private static String sentMsgsSQL = "select * from messages where fromid=? order by date desc;";

    public List<Messages> getSentMsgsByEntryNumber(String fromEntryStringNum) {

        List<Messages> sentMsgList = null;

        openConnection();

        try {
            List<Messages> temp = new LinkedList<Messages>();
            preparedStatement = conn.prepareStatement(sentMsgsSQL);
            preparedStatement.setString(1, fromEntryStringNum);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Messages msg = new Messages();
                msg.setMessageid(rs.getString("messageid"));
                msg.setDate(rs.getString("date"));
                msg.setToid(rs.getString("toid"));
                msg.setStatus(rs.getBoolean("status"));
                msg.setMessage(rs.getString("message"));
                if (msg.getMessageid() != null) {
                    temp.add(msg);
                }
            }
            if (!temp.isEmpty()) {
                sentMsgList = temp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return sentMsgList;
    }

    private static String getSpecificMsgSQL = "select * from messages where messageid=? and (fromid=? or toid=?);";

    public Messages getSpecificMessage(String messageid, String entrynumber) {

        openConnection();

        Messages msg = null;
        System.out.println("AAAAAAAAAAAAA" + messageid + "------------------ " + entrynumber);

        try {
            Messages temp = new Messages();
            preparedStatement = conn.prepareStatement(getSpecificMsgSQL);
            preparedStatement.setLong(1, Long.parseLong(messageid));
            preparedStatement.setString(2, entrynumber);
            preparedStatement.setString(3, entrynumber);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                temp.setMessageid(rs.getString("messageid"));
                temp.setDate(rs.getString("date"));
                temp.setToid(rs.getString("toid"));
                temp.setFromid(rs.getString("fromid"));
                temp.setStatus(rs.getBoolean("status"));
                temp.setMessage(rs.getString("message"));
            }
            if (temp.getMessage() != null) {
                msg = temp;
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return msg;
    }

    String updateMsgStatusSQL = "update messages set status=true where messageid=? and toid=?;";

    public String updateMsgStatus(String messageid, String entrynumber) {
        openConnection();

        try {
            preparedStatement = conn.prepareStatement(updateMsgStatusSQL);
            preparedStatement.setLong(1, Long.parseLong(messageid));
            preparedStatement.setString(2, entrynumber);
            int res = preparedStatement.executeUpdate();
            if (res != 1) {
                return "Nothing updated. Seems messageid is wrong or you are not allowed to read the msg.";
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
}
