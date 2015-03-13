/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.helper;

import java.sql.Timestamp;

/**
 *
 * @author reshma
 */
public class TransactionHistory {
    long TransactionID ;
    String FromID ;
    String ToID;
    long BookID;

    public long getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(long TransactionID) {
        this.TransactionID = TransactionID;
    }

    public String getFromID() {
        return FromID;
    }

    public void setFromID(String FromID) {
        this.FromID = FromID;
    }

    public String getToID() {
        return ToID;
    }

    public void setToID(String ToID) {
        this.ToID = ToID;
    }

    public long getBookID() {
        return BookID;
    }

    public void setBookID(long BookID) {
        this.BookID = BookID;
    }

    public Timestamp getTransStartDate() {
        return TransStartDate;
    }

    public void setTransStartDate(Timestamp TransStartDate) {
        this.TransStartDate = TransStartDate;
    }

    public Timestamp getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(Timestamp LastUpdate) {
        this.LastUpdate = LastUpdate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getBookCondition() {
        return BookCondition;
    }

    public void setBookCondition(String BookCondition) {
        this.BookCondition = BookCondition;
    }
    Timestamp TransStartDate;
    Timestamp LastUpdate;
    String Status;
    String BookCondition;
}
