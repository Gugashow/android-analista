package com.example.analista_nota10.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.analista_nota10.DataBase.Banco;
import com.example.analista_nota10.Model.Login;

public class RegisterService {
    public static final String TABELA = "tbUsuario";
    public static final String ID_USUARIO = "idUsuario";
    public static final String NOME_USUARIO = "nomeUsuario";
    public static final String EMAIL = "email";
    public static final String SENHA = "senha";
    public SQLiteDatabase db;
    public Banco banco;

    public RegisterService(Context context){
        banco = new Banco(context);
    }

    /**
     *
     * @param login
     * @return mensagem
     */
   public String createAccount(Login login){
      ContentValues valores;
      long resultado;

       db = banco.getReadableDatabase();

       if(!banco.tableExists(db, TABELA)){
           onCreate();
       }
       db.close();

       if(getUserByName(login.getNameUser()) != null) return "Registro já existe";

       //
       db = banco.getWritableDatabase();
       valores = new ContentValues();
       valores.put(NOME_USUARIO, login.getNameUser());
       valores.put(EMAIL, login.getEmail());
       valores.put(SENHA, login.getPasswordUser());


       resultado = db.insert(TABELA, null, valores);
       db.close();

       if (resultado == -1) {
           return "Erro ao inserir registro";
       } else {
           return "Registro Inserido com sucesso";
       }

   }

    /**
     * List all users
     * @return cursor
     */
    public Cursor getUsers(){
        Cursor cursor;
        String[] campos = {ID_USUARIO, NOME_USUARIO, EMAIL};

        db = banco.getReadableDatabase();
        cursor = db.query(TABELA, campos,null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;

    }

    /**
     * Creating users table
     */
    public void onCreate() {
        String sql = " CREATE TABLE IF NOT EXISTS "+TABELA+"("
                + ID_USUARIO + " integer primary key autoincrement,"
                + NOME_USUARIO + " text,"
                + EMAIL + " text,"
                + SENHA + " text"
                +")";
        db.execSQL(sql);
    }

    /**
     * List all users
     * @return cursor
     */
    public Login getUserByName(String name){
        Cursor cursor;
        db = banco.getReadableDatabase();

        cursor = db.query(TABELA, null, null, null, null, null, null);



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
