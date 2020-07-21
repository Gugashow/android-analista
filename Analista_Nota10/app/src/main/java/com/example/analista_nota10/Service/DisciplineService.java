package com.example.analista_nota10.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.analista_nota10.DataBase.Banco;

public class DisciplineService {
    protected static final String TABELA = "tbDisciplina";
    protected static final String ID_DISCIPLINA = "idDisciplina";
    protected static final String ID_URUARIO = "idUsuario";
    protected static final String NOME_DISCIPLINA = "nomeDisciplina";
    private SQLiteDatabase db;
    private Banco banco;

    public DisciplineService(Context context){
        banco = new Banco(context);
    }

    /**
     * Creating a discipline
     * @param name
     * @param idUser
     * @return message
     */
    public String createDiscipline(String name, String idUser) {
        ContentValues values;
        Long result;
        Cursor cursor;

        //Checks if discipline exists
        cursor = listDiscipline(name);

        if (cursor != null) {
            if (!cursor.getString(cursor.getColumnIndexOrThrow(Banco.NOME)).equals(name)){
                return "Disciplina já cadastrada";
            }
        }

        // Writing and reading data in the bank
        db = banco.getWritableDatabase();
        values = new ContentValues();
        values.put(NOME_DISCIPLINA, name);
        values.put(ID_URUARIO, idUser);

        result = db.insert(TABELA, null, values);
        db.close();

        if (result == -1) {
            return "Erro ao inserir registro";
        }

        return "Disciplina cadastrada com sucesso!";

    }

    /**
     * Lists all discipline
     * @param nome
     * @return cursor
     */
    public Cursor listDiscipline(String nome){
        Cursor cursor;

        // Reading data in the bank
        db = banco.getReadableDatabase();
        String[] campos = {ID_DISCIPLINA, ID_URUARIO, NOME_DISCIPLINA};
        cursor = db.query(TABELA, campos,null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;

    }
}
