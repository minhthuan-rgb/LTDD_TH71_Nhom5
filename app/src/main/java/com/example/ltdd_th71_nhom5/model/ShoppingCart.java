package com.example.ltdd_th71_nhom5.model;

public class ShoppingCart {
    private int bookID;
    private String bookTitle;
    private double bookValue;
    private int bookImage;
    private int quantity;

    public ShoppingCart(int bookID, String bookTitle, double bookValue, int bookImage, int quantity) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.bookValue = bookValue;
        this.bookImage = bookImage;
        this.quantity = quantity;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public double getBookValue() {
        return bookValue;
    }

    public void setBookValue(double bookValue) {
        this.bookValue = bookValue;
    }

    public int getBookImage() {
        return bookImage;
    }

    public void setBookImage(int bookImage) {
        this.bookImage = bookImage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
