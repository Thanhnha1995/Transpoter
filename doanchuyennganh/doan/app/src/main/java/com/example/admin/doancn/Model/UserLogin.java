package com.example.admin.doancn.Model;

public class UserLogin {
    private String Email;
    private String matkhau;

    public UserLogin() {
    }

    public UserLogin(String taikhoan, String matkhau) {
        this.Email = taikhoan;
        this.matkhau = matkhau;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
