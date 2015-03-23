/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.dbclasses;

import com.sharethyapp.helper.BookResult;
import com.sharethyapp.helper.Messages;
import com.sharethyapp.helper.WishList;
import com.sharethyapp.helper.UtilitiesHelper;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import com.sharethyapp.helper.TransactionHistory;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author reshma
 */
public class TransactionDB extends DB {

    public String startTransaction(String fromID, String toID, long bookID) {
        openConnection();

        String insertTransactionSQL = "insert into Transactions(FromID, ToID, BookID, TransStartDate, LastUpdate,Status,BookCondition) values (?,?,?,?,?,?,?);";

        try {
            preparedStatement = conn.prepareStatement(insertTransactionSQL);
            preparedStatement.setString(1, fromID);
            preparedStatement.setString(2, toID);
            preparedStatement.setLong(3, bookID);
            preparedStatement.setTimestamp(4, UtilitiesHelper.getCurrentTimeStamp());
            preparedStatement.setTimestamp(5, UtilitiesHelper.getCurrentTimeStamp());
            preparedStatement.setString(6, "R");
            preparedStatement.setString(7, null);

            int res = preparedStatement.executeUpdate();
            if (res != 1) {
                return "Nothing inserted in transaction table seems our mistake.";
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

    String acceptRejectSQL = "update Transactions set status=?, lastupdate=now(), bookcondition=? "
            + "where TransactionID = ?;";

    public String acceptRejectTransaction(String transactionID, String status, String cond) {
        openConnection();

        try {
            preparedStatement = conn.prepareStatement(acceptRejectSQL);
            preparedStatement.setString(1, status);
            preparedStatement.setLong(3, Long.parseLong(transactionID));
            preparedStatement.setString(2, cond);

            int res = preparedStatement.executeUpdate();
            if (res != 1) {
                return "Nothing inserted in while acceptiong rejectiong transaction, seems our mistake.";
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

    public List<TransactionHistory> getBooksRequestedBy(String requester) {

        String getBooksRequestedDetails = "select * from Transactions where FromID=?;";
        List<TransactionHistory> booksRequested = new ArrayList<TransactionHistory>();

        openConnection();

        try {

            preparedStatement = conn.prepareStatement(getBooksRequestedDetails);
            preparedStatement.setString(1, requester.trim());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                TransactionHistory trans = new TransactionHistory();
                trans.setTransactionID(rs.getLong("TransactionID"));
                trans.setFromID(rs.getString("FromID"));
                trans.setToID(rs.getString("ToID"));
                trans.setBookID(rs.getLong("BookID"));
                trans.setTransStartDate(rs.getTimestamp("TransStartDate"));
                trans.setLastUpdate(rs.getTimestamp("LastUpdate"));
                trans.setStatus(rs.getString("Status"));
                trans.setBookCondition(rs.getString("BookCondition"));
                booksRequested.add(trans);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return booksRequested;
    }

    public List<TransactionHistory> getBooksRequestedFrom(String requestees) {

        String getBooksRequestedDetails = "select * from Transactions where ToID=?;";
        List<TransactionHistory> booksRequested = new ArrayList<TransactionHistory>();

        openConnection();

        try {

            preparedStatement = conn.prepareStatement(getBooksRequestedDetails);
            preparedStatement.setString(1, requestees.trim());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TransactionHistory trans = new TransactionHistory();
                trans.setTransactionID(rs.getLong("TransactionID"));
                trans.setFromID(rs.getString("FromID"));
                trans.setToID(rs.getString("ToID"));
                trans.setBookID(rs.getLong("BookID"));
                trans.setTransStartDate(rs.getTimestamp("TransStartDate"));
                trans.setLastUpdate(rs.getTimestamp("LastUpdate"));
                trans.setStatus(rs.getString("Status"));
                trans.setBookCondition(rs.getString("BookCondition"));
                booksRequested.add(trans);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return booksRequested;
    }

    public List<TransactionHistory> getTxnsWithStatus(String status) {
        String txndDetailsSQL = "select * from transactions where status = ? order by lastupdate desc;";
        List<TransactionHistory> booksRequested = new ArrayList<TransactionHistory>();

        openConnection();

        try {

            preparedStatement = conn.prepareStatement(txndDetailsSQL);
            preparedStatement.setString(1, status.trim());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                TransactionHistory trans = new TransactionHistory();
                trans.setTransactionID(rs.getLong("TransactionID"));
                trans.setFromID(rs.getString("FromID"));
                trans.setToID(rs.getString("ToID"));
                trans.setBookID(rs.getLong("BookID"));
                trans.setTransStartDate(rs.getTimestamp("TransStartDate"));
                trans.setLastUpdate(rs.getTimestamp("LastUpdate"));
                trans.setStatus(rs.getString("Status"));
                trans.setBookCondition(rs.getString("BookCondition"));
                booksRequested.add(trans);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return booksRequested;
    }
    
    public List<TransactionHistory> getPendingTxns() {
        String txndDetailsSQL = "select * from transactions where status <> 'E' and status <> 'C' order by lastupdate desc;";
        List<TransactionHistory> booksRequested = new ArrayList<TransactionHistory>();

        openConnection();

        try {

            preparedStatement = conn.prepareStatement(txndDetailsSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                TransactionHistory trans = new TransactionHistory();
                trans.setTransactionID(rs.getLong("TransactionID"));
                trans.setFromID(rs.getString("FromID"));
                trans.setToID(rs.getString("ToID"));
                trans.setBookID(rs.getLong("BookID"));
                trans.setTransStartDate(rs.getTimestamp("TransStartDate"));
                trans.setLastUpdate(rs.getTimestamp("LastUpdate"));
                trans.setStatus(rs.getString("Status"));
                trans.setBookCondition(rs.getString("BookCondition"));
                booksRequested.add(trans);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return booksRequested;
    }

    public List<WishList> getMyWishList(String entryNum) {

        String getWishListDetails = "select * from WishList,MasterBooks where EntryNumber =? AND MasterBooks.ISBN=WishList.ISBN;";
        List<WishList> booksWished = new ArrayList<WishList>();

        openConnection();

        try {

            preparedStatement = conn.prepareStatement(getWishListDetails);
            preparedStatement.setString(1, entryNum.trim());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                WishList wish = new WishList();
                wish.setIsbn(rs.getString("ISBN"));
                wish.setDate(rs.getDate("Date"));
                wish.setTitle(rs.getString("Title"));

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
