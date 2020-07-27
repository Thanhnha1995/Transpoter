package com.example.admin.apps.model;

public class UserLogin {
    private String Email;
    private String matkhau;

    public UserLogin() {
    }

    public UserLogin(String email, String mk) {
        this.Email = email;
        this.matkhau = mk;
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
