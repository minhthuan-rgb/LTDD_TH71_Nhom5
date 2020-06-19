package com.example.ltdd_th71_nhom5.model;

import java.security.Key;

public class Category {
    String id;
    String categoryName;
    String URL;
    String key;

    public Category() {
    }

    public Category(String id, String categoryName, String URL, String key) {
        this.id = id;
        this.categoryName = categoryName;
        this.URL = URL;
        this.key = key;
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

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
