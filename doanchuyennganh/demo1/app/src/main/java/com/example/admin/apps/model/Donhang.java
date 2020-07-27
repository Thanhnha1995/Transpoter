package com.example.admin.apps.model;

public class Donhang {
    public Integer IDkh;
    public String tenkh;
    public Integer sdthoai;
    public String diachi_kh;
    public String tensanphamkh;
    public Integer soluongkh;
    public String thantienkh;

    public Donhang(int id, String tenkhachhang,int sdt, String diachi,String tensanpham,int soluong,String thantien) {
        this.IDkh=id;
        this.tenkh=tenkhachhang;
        this.sdthoai=sdt;
        this.diachi_kh=diachi;
        this.tensanphamkh=tensanpham;
        this.soluongkh=soluong;
        this.thantienkh=thantien;
    }

    public Integer getIDkh() {
        return IDkh;
    }

    public void setIDkh(Integer IDkh) {
        this.IDkh = IDkh;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public Integer getSdthoai() {
        return sdthoai;
    }

    public void setSdthoai(Integer sdthoai) {
        this.sdthoai = sdthoai;
    }

    public String getDiachi_kh() {
        return diachi_kh;
    }

    public void setDiachi_kh(String diachi_kh) {
        this.diachi_kh = diachi_kh;
    }

    public String getTensanphamkh() {
        return tensanphamkh;
    }

    public void setTensanphamkh(String tensanphamkh) {
        this.tensanphamkh = tensanphamkh;
    }

    public Integer getSoluongkh() {
        return soluongkh;
    }

    public void setSoluongkh(Integer soluongkh) {
        this.soluongkh = soluongkh;
    }

    public String getThantienkh() {
        return thantienkh;
    }

    public void setThantienkh(String thantienkh) {
        this.thantienkh = thantienkh;
    }
}
