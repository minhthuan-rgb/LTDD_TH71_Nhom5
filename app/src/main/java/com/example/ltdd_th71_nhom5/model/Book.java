package com.example.ltdd_th71_nhom5.model;

public class Book {
    private String bookID;
    private String title;
    private double value;
    private int sale;
    private String description;
    private String URL;

    public Book(){

    }

    public Book(String bookID, String title, double value, int sale, String description, String URL) {
        this.bookID = bookID;
        this.title = title;
        this.value = value;
        this.sale = sale;
        this.description = description;
        this.URL = URL;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
