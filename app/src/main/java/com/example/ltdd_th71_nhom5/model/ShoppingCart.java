package com.example.ltdd_th71_nhom5.model;

public class ShoppingCart {
    private Book book;
    private int quantity;
    private double newValue;

    public ShoppingCart(){
    }

    public ShoppingCart(Book book, int quantity, double newValue) {
        this.book = book;
        this.quantity = quantity;
        this.newValue = newValue;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getNewValue() {
        return newValue;
    }

    public void setNewValue(double newValue) {
        this.newValue = newValue;
    }
}
