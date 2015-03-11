/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharethyapp.helper;

import java.util.List;

/**
 *
 * @author reshma
 */
public class BookResult {
    String isbn;
    String title;
    String year;
    String publisher;
    String rating;
    String authors;
    List<String> authorList;
    List<String> reviewList;

    public List<String> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<String> reviewList) {
        this.reviewList = reviewList;
    }

    public List<String> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<String> authorList) {
        this.authorList = authorList;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    
    public BookResult()
    {
        
    }
    
    
    

    public BookResult(String ISBN, String Title, String Year, String Publisher, String Rating,String Authors) {
        this.isbn = ISBN;
        this.title = Title;
        this.year = Year;
        this.publisher = Publisher;
        this.rating = Rating;
        this.authors=Authors;
    }
    
    
    
}
