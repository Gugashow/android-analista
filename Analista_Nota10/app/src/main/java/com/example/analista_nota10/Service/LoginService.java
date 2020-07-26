package com.example.analista_nota10.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.analista_nota10.DataBase.Banco;
import com.example.analista_nota10.Model.Login;

public class LoginService {
    private SQLiteDatabase db;
    private Banco banco;

    public LoginService(Context context){
        banco = new Banco(context);
    }

    public String login(Login login) {
        Cursor cursor;

        //db = banco.getWritableDatabase();

        //long result = db.delete("tbDisciplina", null, null);

        //Verificar se usuario existe
        Login user = lerDado(login.getNameUser());
        if(user != null){
            if (!user.getNameUser().equals(login.getNameUser())
                    && !user.getPasswordUser().equals(login.getPasswordUser())) {
                return  "Usuario ou senha incorreta" + login.getNameUser() + login.getPasswordUser();
            }

            if (!user.getPasswordUser().equals(login.getPasswordUser())) {
                return  "Senha incorreta";
            }
        }

        return "";
    }

    public Login lerDado(String name){
        Cursor cursor;
        db = banco.getReadableDatabase();
        cursor = db.query(Banco.TABELA, null,null, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                Login login = new Login(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));

                if(login.getNameUser().equals(name)){
                    db.close();
                    return login;
                }
                cursor.moveToNext();
            }

        }
        db.close();
        return null;

    }
}
