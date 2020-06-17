package com.example.ltdd_th71_nhom5.model;

import java.security.Key;

public class Category {
    String id;
    String categoryName;
    String URl;

    public Category() {
    }


    public Category(String id, String categoryName, String URl) {
        this.id = id;
        this.categoryName = categoryName;
        this.URl = URl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getURl() {
        return URl;
    }

    public void setURl(String URl) {
        this.URl = URl;
    }
}
