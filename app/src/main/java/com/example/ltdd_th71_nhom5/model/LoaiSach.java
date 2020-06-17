package com.example.ltdd_th71_nhom5.model;

import java.security.Key;

public class LoaiSach {
    String id;
    String tenLoaiSach;
    String URl;

    public LoaiSach() {
    }


    public LoaiSach(String id, String tenLoaiSach, String URl) {
        this.id = id;
        this.tenLoaiSach = tenLoaiSach;
        this.URl = URl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenLoaiSach() {
        return tenLoaiSach;
    }

    public void setTenLoaiSach(String tenLoaiSach) {
        this.tenLoaiSach = tenLoaiSach;
    }

    public String getURl() {
        return URl;
    }

    public void setURl(String URl) {
        this.URl = URl;
    }
}
