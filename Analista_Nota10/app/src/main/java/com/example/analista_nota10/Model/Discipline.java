package com.example.analista_nota10.Model;

public class Discipline {
    private String nameDiscipline;
    private int _id;
    private int _idUser;

    public String getNameDiscipline() {
        return nameDiscipline;
    }

    public void setNameDiscipline(String nameDiscipline) {
        this.nameDiscipline = nameDiscipline;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_idUser() {
        return _idUser;
    }

    public void set_idUser(int _idUser) {
        this._idUser = _idUser;
    }

    public Discipline (){}

    public Discipline(int _id, int _idUser, String nameDiscipline) {
        this._id = _id;
        this._idUser = _idUser;
        this.nameDiscipline = nameDiscipline;
    }

    public String toString(){
        return this.getNameDiscipline();
    }
}
