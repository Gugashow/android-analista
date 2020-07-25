package com.example.analista_nota10.Model;

public class Alternative {
    private String[] alternatives;
    private int _id;
    private int _idResposta;
    private int _idQuestoes;

    public Alternative() {}

    public String[] getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(String[] alternatives) {
        this.alternatives = alternatives;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_idResposta() {
        return _idResposta;
    }

    public void set_idResposta(int _idResposta) {
        this._idResposta = _idResposta;
    }

    public int get_idQuestoes() {
        return _idQuestoes;
    }

    public void set_idQuestoes(int _idQuestoes) {
        this._idQuestoes = _idQuestoes;
    }
}
