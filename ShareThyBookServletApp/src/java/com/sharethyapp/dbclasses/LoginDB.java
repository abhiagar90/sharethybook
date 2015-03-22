/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.dbclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abhishek
 */
public class LoginDB extends DB {

    //Login is the materialized view we have created
    private final String getPasswdSQL = "select password from Login where entrynumber ilike ?";

    public String getPassword(String entryNum) //returns null if exception
    {
        openConnection();
        System.out.println("eeeeeeeeee: "+entryNum);

        String password = null;
        try {
            preparedStatement = conn.prepareStatement(getPasswdSQL);
            preparedStatement.setString(1, entryNum);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                password = rs.getString("Password");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        System.out.println("ppppppppppppppppp: "+password);
        return password;
    }

    public static void main(String[] args) {
        System.out.println(new LoginDB().getPassword("2014MCS1001"));
        System.out.println("aage");
    }
}
