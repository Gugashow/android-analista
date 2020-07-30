package com.example.analista_nota10.Model;

import android.widget.AutoCompleteTextView;

public class Alternative {
    private String alternative;
    private int _id;
    private String resposta;
    private String respostaUser;
    private int _idQuestoes;
    private AutoCompleteTextView view;

    public Alternative() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_idQuestoes() {
        return _idQuestoes;
    }

    public void set_idQuestoes(int _idQuestoes) {
        this._idQuestoes = _idQuestoes;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public AutoCompleteTextView getView() {
        return view;
    }

    public void setView(AutoCompleteTextView view) {
        this.view = view;
    }

    public String getRespostaUser() {
        return respostaUser;
    }

    public void setRespostaUser(String respostaUser) {
        this.respostaUser = respostaUser;
    }

    public Alternative(int _id, String alternative, String resposta, int _idQuestoes) {
        this._id = _id;
        this.alternative = alternative;
        this.resposta = resposta;
        this._idQuestoes = _idQuestoes;
    }

    public Alternative(String alternative, String resposta, AutoCompleteTextView view) {
        this.alternative = alternative;
        this.resposta = resposta;
        this.view = view;
    }

}