package com.example.analista_nota10.Model;

public class Login {
    private String nameUser;
    private String passwordUser;
    private String email;
    private int _id;

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String name) {
        this.nameUser = name;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String password) {
        this.passwordUser = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int get_Id() {
        return _id;
    }

    public void set_Id(int id) {
        this._id = id;
    }

    public Login(){}

    public Login(int _id, String nameUser, String email, String passwordUser) {
        this._id = _id;
        this.nameUser = nameUser;
        this.email = email;
        this.passwordUser = passwordUser;
    }


}
