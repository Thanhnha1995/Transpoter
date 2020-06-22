package com.example.admin.doancn.Model;

import java.io.Serializable;

public class Tepkh implements Serializable {
    public String tenkh;
    public Integer phones;
    public  String diachis;
    public  Integer ID;
    public Integer sl;
    public String tensp;
    public Integer dongia;

    public Tepkh() {

    }


    public  Tepkh(String tenkhachhang, Integer sdt, String diachi,
                  Integer id, int soluong, int thantien, String tensanpham){
        this.ID= id;
        this.sl = soluong;
        this.dongia=thantien;
        this.tensp=tensanpham;
        this.tenkh = tenkhachhang;
        this.phones = sdt;
        this.diachis = diachi;
    }



    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getSl() {
        return sl;
    }

    public void setSl(Integer sl) {
        this.sl = sl;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public Integer getDongia() {
        return dongia;
    }

    public void setDongia(Integer dongia) {
        this.dongia = dongia;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public Integer getPhones() {
        return phones;
    }

    public void setPhones(Integer phones) {
        this.phones = phones;
    }

    public String getDiachis() {
        return diachis;
    }

    public void setDiachis(String diachis) {
        this.diachis = diachis;
    }
}
