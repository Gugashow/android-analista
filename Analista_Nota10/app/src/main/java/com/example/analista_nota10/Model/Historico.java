package com.example.analista_nota10.Model;

import java.io.Serializable;

public class Historico implements Serializable {
    private int _id;
    private int _idDiscipline;
    private int _idUser;
    private int correctAnswers;
    private float percentage;
    private int qtdQuestions;

    public Historico() {}

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

    public int get_idUser() {
        return _idUser;
    }

    public void set_idUser(int _idUser) {
        this._idUser = _idUser;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getQtdQuestions() {
        return qtdQuestions;
    }

    public void setQtdQuestions(int qtdQuestions) {
        this.qtdQuestions = qtdQuestions;
    }

    public Historico(int _idDiscipline, int _idUser, int correctAnswers, float percentage, int qtdQuestions) {
        this._idDiscipline = _idDiscipline;
        this._idUser = _idUser;
        this.correctAnswers = correctAnswers;
        this.percentage = percentage;
        this.qtdQuestions = qtdQuestions;
    }

    public Historico(int _id, int _idDiscipline, int _idUser, int correctAnswers, int qtdQuestions) {
        this._id = _id;
        this._idDiscipline = _idDiscipline;
        this._idUser = _idUser;
        this.correctAnswers = correctAnswers;
        this.qtdQuestions = qtdQuestions;
    }
}
