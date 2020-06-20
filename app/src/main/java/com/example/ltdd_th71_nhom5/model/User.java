package com.example.ltdd_th71_nhom5.model;

import java.util.List;

public class    User {
    String userId;
    String userName;
    String diaChi;
    String phone;
    String id;
    String passWord;
    List<Order> orDer;

    public User() {
    }

    public User(String userId, String userName, String diaChi, String phone, String id, String passWord, List<Order> orDer) {
        this.userId = userId;
        this.userName = userName;
        this.diaChi = diaChi;
        this.phone = phone;
        this.id = id;
        this.passWord = passWord;
        this.orDer = orDer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public List<Order> getOrDer() {
        return orDer;
    }

    public void setOrDer(List<Order> orDer) {
        this.orDer = orDer;
    }
}
