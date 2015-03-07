/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.dbclasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abhishek
 */
public class UserTableDB extends DB{
    //Login is the materialized view we have created
    private final String getAllDetailsSQL = "select * from UserTable where entrynumber=?";

    public UserTable getDetailsfromEntryNum(String entryNum) //returns null if exception
    {
        openConnection();

        UserTable user = null;
        try {
            UserTable temp = new UserTable();
            preparedStatement = conn.prepareStatement(getAllDetailsSQL);
            preparedStatement.setString(1, entryNum);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                temp.setEntrynumber(entryNum);
                temp.setEmailId(rs.getString("emailid"));
                temp.setFirstname(rs.getString("firstname"));
                temp.setLastname(rs.getString("lastname"));
                temp.setIsHosteler(rs.getBoolean("hosteler"));
                temp.setHouseNo(rs.getString("houseno"));
                temp.setStreetNo(rs.getString("streetno"));
                temp.setCity(rs.getString("city"));
                temp.setState(rs.getString("state"));
                temp.setPincode(rs.getString("pincode"));
                temp.setTypeOfUser(rs.getInt("typeofuser"));
                temp.setUnreadMsgs(rs.getInt("unreadmessages"));
                temp.setBooksContri(rs.getInt("bookscontributed"));
                temp.setBooksHave(rs.getInt("bookshave"));
                temp.setProfileImgage(rs.getBytes("photo"));
            }
            user=temp;
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return user;
    }

    public static void main(String[] args) {
        UserTable utb = new UserTableDB().getDetailsfromEntryNum("2013SMN1593");
        System.out.println(utb.getProfileImgage().length);
       }
    
}
