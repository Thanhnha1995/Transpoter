package com.example.admin.doancn.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class KhachHangInfo implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("tenkhachhang")
    @Expose
    private String tenkhachhang;
    @SerializedName("sdt")
    @Expose
    private int sdt;
    @SerializedName("diachi")
    @Expose
    private String diachi;
    @SerializedName("tensanpham")
    @Expose
    private String tensanpham;
    @SerializedName("soluong")
    @Expose
    private int soluong;
    @SerializedName("thantien")
    @Expose
    private int thantien;

    /**
     * No args constructor for use in serialization
     *
     */
    public KhachHangInfo() {
    }

    /**
     *
     * @param id
     * @param tensanpham
     * @param sdt
     * @param tenkhachhang
     * @param thantien
     * @param soluong
     * @param diachi
     */
    public KhachHangInfo(int id, String tenkhachhang, int sdt, String diachi, String tensanpham, int soluong, int thantien) {
        super();
        this.id = id;
        this.tenkhachhang = tenkhachhang;
        this.sdt = sdt;
        this.diachi = diachi;
        this.tensanpham = tensanpham;
        this.soluong = soluong;
        this.thantien = thantien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getThantien() {
        return thantien;
    }

    public void setThantien(int thantien) {
        this.thantien = thantien;
    }
}
