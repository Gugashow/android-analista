package com.example.analista_nota10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class BancoController {
    private SQLiteDatabase db;
    private Banco banco;

    public BancoController(Context context){
        banco = new Banco(context);
    }

   /* public String inserirQuestoes(String titulo, String autor, String editora){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.TITULO, titulo);
        valores.put(CriaBanco.AUTOR, autor);
        valores.put(CriaBanco.EDITORA, editora);

        resultado = db.insert(CriaBanco.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso”;

    }
    */
   @RequiresApi(api = Build.VERSION_CODES.O)

   public void insereDado(String titulo, String autor, String editora){
    //   ContentValues valores;
     //  long resultado;

      db = banco.getWritableDatabase();

       banco.onCreate();

      // resultado = db.(Banco.TABELA, null, valores);
       //db.close();

   }
}
