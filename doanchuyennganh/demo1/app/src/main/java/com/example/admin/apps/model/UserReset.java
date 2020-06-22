package com.example.admin.apps.model;

public class UserReset {
    private String Email;
    private String matkhau;
    public UserReset(){

    }
    public UserReset(String email, String mk) {
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
