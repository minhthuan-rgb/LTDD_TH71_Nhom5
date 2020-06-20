package com.example.ltdd_th71_nhom5.model;

import java.util.List;

public class Order {
    String orderId;
    String Date;
    List<ShoppingCart> listCart;

    public Order() {
    }

    public Order(String orderId, String date, List<ShoppingCart> listCart) {
        this.orderId = orderId;
        Date = date;
        this.listCart = listCart;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public List<ShoppingCart> getListCart() {
        return listCart;
    }

    public void setListCart(List<ShoppingCart> listCart) {
        this.listCart = listCart;
    }
}
