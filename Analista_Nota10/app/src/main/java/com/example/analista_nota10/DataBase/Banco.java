package com.example.analista_nota10.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Banco extends SQLiteOpenHelper{

    public static final String NOME_BANCO = "analista.db";
    public static final String TABELA = "tbUsuario";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String EMAIL = "email";
    public static final String SENHA = "senha";
    public static final int VERSAO = 1;
    private SQLiteDatabase db;

    public Banco (Context context){

        super(context, NOME_BANCO,null,VERSAO);
    }

    /*@RequiresApi(api = Build.VERSION_CODES.O)

    public void onCreate (){

        try {
            String sql = new String(Files.readAllBytes(Paths.get("src/main/java/com/example/analista_nota10/initialize.sql")), StandardCharsets.UTF_8);

            for (String line: sql.split(";")) {

                db.execSQL(line);
            }

        }catch (IOException erro){
            erro.printStackTrace();
        }

    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + NOME + " text,"
                + EMAIL + " text,"
                + SENHA + " text"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbUsuario");
        onCreate(db);

    }
}
