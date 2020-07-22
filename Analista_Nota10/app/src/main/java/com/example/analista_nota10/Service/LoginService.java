package com.example.analista_nota10.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.analista_nota10.DataBase.Banco;

public class LoginService {
    private SQLiteDatabase db;
    private Banco banco;

    public LoginService(Context context){
        banco = new Banco(context);
    }

    public String login(String nome, String senha) {
        Cursor cursor;

        db = banco.getWritableDatabase();

        //Verificar se usuario existe
        cursor = lerDado(nome);

        if (cursor != null) {
            if (!cursor.getString(cursor.getColumnIndexOrThrow(Banco.NOME)).equals(nome)
                    && !cursor.getString(cursor.getColumnIndexOrThrow(Banco.SENHA)).equals(senha)) {
                return  "Usuario ou senha incorreta";
            }

            if (!cursor.getString(cursor.getColumnIndexOrThrow(Banco.SENHA)).equals(senha)) {
                return  "Senha incorreta";
            }
        }

        return "";
    }

    public Cursor lerDado(String nome){
        Cursor cursor;
        db = banco.getReadableDatabase();
        String[] campos = {Banco.ID, Banco.NOME, Banco.EMAIL, Banco.SENHA};
        cursor = db.query(Banco.TABELA, campos,null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;

    }
}
