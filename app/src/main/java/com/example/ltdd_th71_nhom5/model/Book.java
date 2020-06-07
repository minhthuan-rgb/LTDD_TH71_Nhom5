package com.example.ltdd_th71_nhom5.model;

public class Book {
    private int bookID;
    private String title;
    private  int categoryID;
    private double value;
    private int sale;
    private String description;
    private int imgID;

    public Book(){
    }

    public Book(int bookID, String title, int categoryID, double value, int sale, String description, int imgID){
        this.bookID = bookID;
        this.title = title;
        this.categoryID = categoryID;
        this.value = value;
        this.sale = sale;
        this.description = description;
        this.imgID = imgID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
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

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
}
