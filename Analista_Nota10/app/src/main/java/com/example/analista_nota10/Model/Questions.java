package com.example.analista_nota10.Model;

import java.util.List;

public class Questions {
    private List<Alternative> alternatives;
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

    public List<Alternative> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Alternative> alternatives) {
        this.alternatives = alternatives;
    }

    public Questions(int _id, String question, int _idDiscipline) {
        this._id = _id;
        this.question = question;
        this._idDiscipline = _idDiscipline;
    }

    public Questions(String question, int _idDiscipline) {
        this.question = question;
        this._idDiscipline = _idDiscipline;
    }
}
