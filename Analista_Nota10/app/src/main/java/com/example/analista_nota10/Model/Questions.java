package com.example.analista_nota10.Model;

public class Questions {
    private String question;
    private int _id;
    private int _idDiscipline;

    public Questions (){}

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_idDiscipline() {
        return _idDiscipline;
    }

    public void set_idDiscipline(int _idDiscipline) {
        this._idDiscipline = _idDiscipline;
    }
}
