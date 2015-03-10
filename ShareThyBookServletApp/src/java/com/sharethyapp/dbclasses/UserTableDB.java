/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.dbclasses;

import com.sharethyapp.helper.UtilitiesHelper;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author abhishek
 */
public class UserTableDB extends DB {

    //Login is the materialized view we have created
    private final String getAllDetailsSQL = "select * from UserTable where entrynumber=?";
    private final String getProfileImageSQL = "select photo from UserTable where entrynumber=?";

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
                temp.setProfileImage(rs.getBytes("photo"));
                user = temp;
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return user;
    }

    public byte[] getImagefromEntryNum(String entryNum) //returns null if exception
    {
        openConnection();

        byte[] photo = null;

        try {
            byte[] temp = null;
            preparedStatement = conn.prepareStatement(getAllDetailsSQL);
            preparedStatement.setString(1, entryNum);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                temp = (rs.getBytes("photo"));
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

    public String addNewUser(UserTable newUser, String imagePath) throws NoSuchAlgorithmException, IOException //returns null if exception
    {
        openConnection();

        newUser.setPassword(UtilitiesHelper.getMD5Hash(newUser.getPassword()));       
        File file = new File(imagePath);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("the Anonymous File Does not exist.Please Correct Its Location");
        } catch (Exception e) {
            System.out.println("Exception in reading file :: " + e.getMessage() + " " + e.getStackTrace());
        }
        

        String prepQuery = "Insert into UserTable Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            preparedStatement = conn.prepareStatement(prepQuery);
            preparedStatement.setString(1, newUser.getEntrynumber());
            preparedStatement.setString(2, newUser.getPassword());
            preparedStatement.setString(3, newUser.getFirstname());
            preparedStatement.setString(4, newUser.getLastname());
            preparedStatement.setBoolean(5, newUser.isIsHosteler());
            preparedStatement.setString(6, newUser.getHouseNo());
            preparedStatement.setString(7, newUser.getStreetNo());
            preparedStatement.setString(8, newUser.getCity());
            preparedStatement.setString(9, newUser.getState());
            preparedStatement.setString(10, newUser.getPincode());
            preparedStatement.setString(11, newUser.getEmailId());
            preparedStatement.setInt(12, 3);
            preparedStatement.setInt(13, 0);
            preparedStatement.setInt(14, 0);
            preparedStatement.setInt(15, 0);
            preparedStatement.setBinaryStream(16, fis, (int) file.length());
            int res = preparedStatement.executeUpdate();
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

    public static void main(String[] args) {
        UserTable utb = new UserTableDB().getDetailsfromEntryNum("2013SMN1593");
        System.out.println(utb.getProfileImage().length);
    }

}
