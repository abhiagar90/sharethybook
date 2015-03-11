/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.helper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author reshma
 */
public class BookSearchQueryResult {
    List<BookResult> bookRes;
    String BookSearchQuery;

    public List<BookResult> getBookRes() {
        return bookRes;
    }

    public void setBookRes(List<BookResult> bookRes) {
        this.bookRes = bookRes;
    }

    public String getBookSearchQuery() {
        return BookSearchQuery;
    }

    public void setBookSearchQuery(String BookSearchQuery) {
        this.BookSearchQuery = BookSearchQuery;
    }
}
