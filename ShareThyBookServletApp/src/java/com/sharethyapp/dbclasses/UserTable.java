/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.dbclasses;

import java.util.List;

/**
 *
 * @author abhishek
 */
public class UserTable {
    
    private String entrynumber;
    private String password;
    private String firstname;
    private String lastname;
    private boolean isHosteler;
    private String houseNo;
    private String streetNo;
    private String city;
    private String state;
    private String pincode;
    private String emailId;
    private int typeOfUser;
    private int unreadMsgs;
    private int booksContri;
    private int booksHave;
    private byte[] profileImage;
    
    
    
    public String getEntrynumber() {
        return entrynumber;
    }

    public void setEntrynumber(String entrynumber) {
        this.entrynumber = entrynumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isIsHosteler() {
        return isHosteler;
    }

    public void setIsHosteler(boolean isHosteler) {
        this.isHosteler = isHosteler;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(int typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public int getUnreadMsgs() {
        return unreadMsgs;
    }

    public void setUnreadMsgs(int unreadMsgs) {
        this.unreadMsgs = unreadMsgs;
    }

    public int getBooksContri() {
        return booksContri;
    }

    public void setBooksContri(int booksContri) {
        this.booksContri = booksContri;
    }

    public int getBooksHave() {
        return booksHave;
    }

    public void setBooksHave(int booksHave) {
        this.booksHave = booksHave;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
    
    
}
