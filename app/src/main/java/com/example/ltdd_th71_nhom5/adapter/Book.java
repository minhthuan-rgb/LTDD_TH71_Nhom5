package com.example.ltdd_th71_nhom5.adapter;

public class Book {
    private String title;
    private  String category;
    private double value;
    private String supplier;
    private String sale;
    private String description;
    private int imgID;

    public Book(){
    }

    public Book(String title, String category, double value, String supplier, String sale, String description, int imgID){
        this.title = title;
        this.category = category;
        this.value = value;
        this.supplier = supplier;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
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
}
