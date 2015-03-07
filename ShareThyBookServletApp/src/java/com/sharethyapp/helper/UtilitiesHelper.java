/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abhishek
 */
public class UtilitiesHelper {

    public static String getMD5Hash(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UtilitiesHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        md.update(password.getBytes());
        byte[] digest = md.digest();

        StringBuffer sb1 = new StringBuffer();
        for (byte b : digest) {
            sb1.append(String.format("%02x", b & 0xff));
        }
        return sb1.toString();
    }
}
