/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.helper;

/**
 *
 * @author abhishek
 */
public class PhysicalBooks {

    private String bookidPhysical;
    private String isbn;
    private String ownerid;
    private String holderid;
    public String holdingdate;
    public String lastCondition;

    public String getBookidPhysical() {
        return bookidPhysical;
    }

    public void setBookidPhysical(String bookidPhysical) {
        this.bookidPhysical = bookidPhysical;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }

    public String getHolderid() {
        return holderid;
    }

    public void setHolderid(String holderid) {
        this.holderid = holderid;
    }

    public String getHoldingdate() {
        return holdingdate;
    }

    public void setHoldingdate(String holdingdate) {
        this.holdingdate = holdingdate;
    }

    public String getLastCondition() {
        return lastCondition;
    }

    public void setLastCondition(String lastCondition) {
        this.lastCondition = lastCondition;
    }
    
    
}
