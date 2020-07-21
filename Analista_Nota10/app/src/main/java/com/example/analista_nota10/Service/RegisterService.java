package com.example.analista_nota10.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.analista_nota10.DataBase.Banco;

public class RegisterService {
    private SQLiteDatabase db;
    private Banco banco;

    public RegisterService(Context context){
        banco = new Banco(context);
    }

    /**
     *
     * @param nome
     * @param email
     * @param senha
     * @return mensagem
     */
   public String createAccount(String nome, String email, String senha){
      ContentValues valores;
      long resultado;

      db = banco.getWritableDatabase();
       valores = new ContentValues();
       valores.put(Banco.NOME, nome);
       valores.put(Banco.EMAIL, email);
       valores.put(Banco.SENHA, senha);


      resultado = db.insert(Banco.TABELA, null, valores);
       db.close();

       if (resultado ==-1) {
           return "Erro ao inserir registro";
       }else {
           return "Registro Inserido com sucesso";
       }
   }

    /**
     *
     * @return cursor
     */
    public Cursor lerDado(){
        Cursor cursor;
        String[] campos = {Banco.ID, Banco.NOME, Banco.EMAIL};

        db = banco.getReadableDatabase();
        cursor = db.query(Banco.TABELA, campos,null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;

    }
}
