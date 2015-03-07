/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.helper;

import com.sharethyapp.dbclasses.LoginDB;

/**
 *
 * @author abhishek
 */
public class LoginHelper {

    public boolean verifyLogin(String entryNum, String Password) {
        String fromDB = new LoginDB().getPassword(entryNum);
        if (fromDB == null) {
            return false;
        } else {
            return (UtilitiesHelper.getMD5Hash(Password)).equals(fromDB);
        }
    }

}
