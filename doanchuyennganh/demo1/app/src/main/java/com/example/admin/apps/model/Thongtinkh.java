package com.example.admin.apps.model;

public class Thongtinkh {
    String tenkhachhang;
    String sdt;
    String email;
    String diachi;
    String matkhau;



    public Thongtinkh(String tenkh, String sdt, String email, String diachi, String mk) {
        this.tenkhachhang = tenkh;
        this.sdt = sdt;
        this.email = email;
        this.diachi = diachi;
        this.matkhau = mk;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
